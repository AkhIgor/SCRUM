package com.igor.scrumassistant.presentation.fragment;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.data.constants.Swipe;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.CommonSceneFragmentView;

import java.util.ArrayList;
import java.util.List;

@InjectViewState
public abstract class CommonSceneFragmentPresenter extends MvpPresenter<CommonSceneFragmentView> {

    private List<Task> mTaskList = new ArrayList<>();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        initList();
    }

    //Retrofit
    abstract void getList();

    //Maybe not needed
    abstract void filterList();

    abstract void onTaskSwiped(@NonNull Swipe swipe);

    public void updateList() {
        getList();
        filterList();
        getViewState().updateTaskList(mTaskList);
    }

    public void onAddChangedTaskStateToList(int position) {
        getViewState().addTaskToList(mTaskList.get(position));
    }

    private void initList() {
        getList();
        filterList();
        getViewState().setTaskList(mTaskList);
    }

    private void changeTaskState(@NonNull Priority priority, int taskPosition) {
        Task task = mTaskList.get(taskPosition);
        task.setPriority(priority);
        getViewState().removeTaskFromList(taskPosition);
        getViewState().notifyTaskPriorityChanged(priority, taskPosition);
    }
}
