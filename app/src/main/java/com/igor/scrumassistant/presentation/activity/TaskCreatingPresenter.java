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
import com.igor.scrumassistant.data.constants.Role;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.data.provider.database.ApiDatabaseProvider;
import com.igor.scrumassistant.model.entity.AppEntity;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.TaskCreatingActivityView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public class TaskCreatingPresenter extends MvpPresenter<TaskCreatingActivityView>
        implements Customer, LoaderManager.LoaderCallbacks<List<Executor>> {

    private ApiDatabaseProvider mProvider;
    private Context mContext;
    private LoaderManager mLoaderManager;

    public TaskCreatingPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
//        mProvider = new ApiDatabaseProvider()
        //запрос на пользователей
//        mProvider.getExecutorsInTheProject(this);

        mLoaderManager.initLoader(0, null, this).forceLoad();
    }

    public void onSaveButtonClicked() {
        getViewState().addTaskToList();
    }

    public void addTask(@NonNull Task task) {
        saveTask(task);
    }

    private void saveTask(@NonNull Task task) {
        mProvider.addTask(task);
    }

    @Override
    public void setEntity(@NonNull AppEntity entity) {

    }

    @Override
    public void setList(@NonNull List list) {

    }

    @NonNull
    @Override
    public Loader<List<Executor>> onCreateLoader(int i, @Nullable Bundle bundle) {
//        return null;
        return new ListInitial(mContext);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Executor>> loader, List<Executor> executorList) {
        Executor executor = new Executor();
        executor.setName("Гарри");
        executor.setSurname("Поттер");
        executor.setRole(Role.SCRUM_MASTER);
        executor.setPassword("123");

        Executor executor1 = new Executor();
        executor1.setName("Брюс");
        executor1.setSurname("Уэйн");
        executor1.setRole(Role.DESIGNER);
        executor1.setPassword("123");

        Executor executor2 = new Executor();
        executor2.setName("Джон");
        executor2.setSurname("Уик");
        executor2.setRole(Role.ANALYTIC);
        executor2.setPassword("123");

        Executor executor3 = new Executor();
        executor3.setName("Джейсон");
        executor3.setSurname("Стетхем");
        executor3.setRole(Role.PRODUCT_OWNER);
        executor3.setPassword("123");

        Executor executor4 = new Executor();
        executor4.setName("Тони");
        executor4.setSurname("Старк");
        executor4.setRole(Role.DEVELOPER);
        executor4.setPassword("123");

        executorList.add(executor);
        executorList.add(executor1);
        executorList.add(executor2);
        executorList.add(executor3);
        executorList.add(executor4);

        getViewState().setListEnabled((List<Executor>) executorList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Executor>> loader) {

    }

    private static class ListInitial extends AsyncTaskLoader<List<Executor>> {

        private Database mDatabase;
        private long mProjectId;
        private List<Executor> executors = new ArrayList<>();

        ListInitial(@NonNull Context context) {
            super(context);

            mDatabase = Database.initDataBase(context);
            mProjectId = CurrentUser.getProjectId(context);
        }

        @Nullable
        @Override
        public List<Executor> loadInBackground() {
            List<Long> participants = mDatabase.teamDao().getParticipantsByProject(mProjectId);
            if (participants != null) {
                for (Long id : participants) {
                    executors.add(mDatabase.executorDao().getExecutorById(id));
                }
            }
            return executors;
        }
    }
}
