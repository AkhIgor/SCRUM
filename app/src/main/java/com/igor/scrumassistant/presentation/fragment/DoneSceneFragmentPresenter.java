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

import static com.igor.scrumassistant.data.constants.State.DONE;

@InjectViewState
public class DoneSceneFragmentPresenter extends CommonSceneFragmentPresenter {

    public DoneSceneFragmentPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        super(context, loaderManager);
    }

    @Override
    public void onTaskSwiped(@NonNull Swipe swipe, int position) {
        if(swipe == Swipe.LEFT) {
            changeTaskState(State.IN_WORK, position);
        } else {
            getViewState().cancelRemovement();
        }
    }

    @Override
    public void setEntity(@NonNull AppEntity entity) {

    }

    @Override
    protected void getList() {
//        mProvider.getDoneTasks(this);
        new Thread(() -> {
            long projectId = CurrentUser.getProjectId(mContext);
            mTaskList = Database.initDataBase(mContext).taskDao().getTaskByStateInProject(projectId, DONE.getValue());
            if(mTaskList == null) {
                mTaskList = new LinkedList<>();
            }
            mHandler.sendEmptyMessage(LIST_READ);
        }).start();
    }
}
