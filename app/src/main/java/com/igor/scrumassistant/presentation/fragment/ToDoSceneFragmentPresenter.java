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

import static com.igor.scrumassistant.data.constants.State.OPEN;

@InjectViewState
public class ToDoSceneFragmentPresenter extends CommonSceneFragmentPresenter {

    public ToDoSceneFragmentPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        super(context, loaderManager);

    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

    }

    @Override
    public void onTaskSwiped(@NonNull Swipe swipe, int position) {
        if (swipe == Swipe.RIGHT) {
            changeTaskState(State.IN_WORK, position);
        }
    }

    @Override
    public void setEntity(@NonNull AppEntity entity) {
        // не требуется реализация. Пока что...

    }

    // чтение из БД и отправка в Handler
    @Override
    protected void getList() {
//        mProvider.getOpenTasks(this);
        new Thread(() -> {
            long projectId = CurrentUser.getProjectId(mContext);
            mTaskList = Database.initDataBase(mContext).taskDao().getTaskByStateInProject(projectId, OPEN.getValue());
            mHandler.sendEmptyMessage(LIST_READ);
        }).start();
    }
}
