package com.igor.scrumassistant.presentation.fragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import com.arellomobile.mvp.InjectViewState;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.data.constants.Swipe;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.AppEntity;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.presentation.fragment.parent.CommonSceneFragmentPresenter;

import java.util.LinkedList;

import static com.igor.scrumassistant.data.constants.State.IN_WORK;

@InjectViewState
public class InWorkSceneFragmentPresenter extends CommonSceneFragmentPresenter {

    public InWorkSceneFragmentPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        super(context, loaderManager);
    }

    @Override
    public void onTaskSwiped(@NonNull Swipe swipe, int position) {
        if (swipe == Swipe.RIGHT) {
            changeTaskState(State.DONE, position);
        } else {
            changeTaskState(State.OPEN, position);
        }
    }

    @Override
    public void setEntity(@NonNull AppEntity entity) {

    }

    @Override
    protected void getList() {
//        mProvider.getInWorkTasks(this);
        new Thread(() -> {
            long projectId = CurrentUser.getProjectId(mContext);
            mTaskList = Database.initDataBase(mContext).taskDao().getTaskByStateInProject(projectId, IN_WORK.getValue());
            if(mTaskList == null) {
                mTaskList = new LinkedList<>();
            }
            mHandler.sendEmptyMessage(LIST_READ);
        }).start();
    }
}
