package com.igor.scrumassistant.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.view.ChoosingProjectActivityView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

@InjectViewState
public class ChoosingProjectActivityPresenter extends MvpPresenter<ChoosingProjectActivityView> {

    private final static int LIST_IS_EMPTY = 0;
    private final static int LIST_IS_NOT_EMPTY = 1;

    private List<Project> mProjectList = new ArrayList<>();
    private Context mContext;
    private ProjectListProvider mProvider;

    public ChoosingProjectActivityPresenter(@NonNull Context context) {
        mContext = context;
        mProvider = new ProjectListProvider(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        new Thread(() -> {
            Database database = Database.initDataBase(mContext);
            List<Long> projectIds = database.teamDao().getProjectsByParticipants(CurrentUser.getUserId(mContext));
            for(Long projectId : projectIds) {
                Project project = database.projectDao().getProjectById(projectId);
                mProjectList.add(project);
            }
            if(mProjectList != null && !mProjectList.isEmpty()) {
                mProvider.sendEmptyMessage(LIST_IS_NOT_EMPTY);
            } else {
                mProvider.sendEmptyMessage(LIST_IS_EMPTY);
            }
        }).start();
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && data!= null) {
            Project project = data.getParcelableExtra(Project.class.getCanonicalName());
            if(project != null) {
                mProjectList.add(project);
                getViewState().addProjectToList(project);
            }
        }
    }

    private void showList() {
        getViewState().setList(mProjectList);
    }

    private void createNewProject() {
        getViewState().createProject();
    }

    private static class ProjectListProvider extends Handler {

        private final WeakReference<ChoosingProjectActivityPresenter> mPresenterRef;

        ProjectListProvider(@NonNull ChoosingProjectActivityPresenter presenter) {
            mPresenterRef = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case LIST_IS_EMPTY: {
                    mPresenterRef.get().createNewProject();
                    break;
                }
                case LIST_IS_NOT_EMPTY: {
                    mPresenterRef.get().showList();
                    break;
                }
            }
        }
    }
}
