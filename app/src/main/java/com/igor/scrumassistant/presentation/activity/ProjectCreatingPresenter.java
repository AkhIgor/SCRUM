package com.igor.scrumassistant.presentation.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.AppEntity;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Team;
import com.igor.scrumassistant.view.ProjectCreatingActivityView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class ProjectCreatingPresenter extends MvpPresenter<ProjectCreatingActivityView>
        implements Customer, LoaderManager.LoaderCallbacks<List<Executor>> {

    //    private CommonDataProvider mProvider;
    private Context mContext;
    private Database mDatabase;
    private LoaderManager mLoaderManager;

    public ProjectCreatingPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
        mDatabase = Database.initDataBase(context);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        super.onFirstViewAttach();
//        mProvider = CommonDataProvider.getInstance();
        //запрос на пользователей
//        mProvider.getExecutorsInTheProject(this);

        mLoaderManager.initLoader(0, null, this).forceLoad();
    }

    public void onCheckOutButtonClicked() {
        getViewState().checkOut();
    }

    public void addProject(@NonNull Project project) {
//        mProvider.addProject(project);
        new Thread(() -> {
                mDatabase.projectDao().addProject(project);
                long projectId = mDatabase.projectDao().getProjectIdByName(project.getName());
                Team team = new Team(projectId, CurrentUser.getUserId(mContext));
                mDatabase.teamDao().addTeam(team);
        }).start();
    }

    @Override
    public void setEntity(@NonNull AppEntity entity) {

    }

    @Override
    public void setList(@NonNull List list) {
        getViewState().setListEnabled((List<Executor>) list);
    }

    @NonNull
    @Override
    public Loader<List<Executor>> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new ListInitial(mContext, mDatabase);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Executor>> loader, List<Executor> executorList) {
        getViewState().setListEnabled((List<Executor>) executorList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Executor>> loader) {

    }

    private static class ListInitial extends AsyncTaskLoader<List<Executor>> {

        private Database mDatabase;
        private List<Executor> mExecutors = new ArrayList<>();

        ListInitial(@NonNull Context context, @NonNull Database database) {
            super(context);
            mDatabase = database;
        }

        @Nullable
        @Override
        public List<Executor> loadInBackground() {
            List<Executor> executors = mDatabase.executorDao().getAll();
            return executors;
        }
    }
}
