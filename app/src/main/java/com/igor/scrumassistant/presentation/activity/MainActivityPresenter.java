package com.igor.scrumassistant.presentation.activity;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.MainActivityView;

import static android.app.Activity.RESULT_OK;

@InjectViewState
public class MainActivityPresenter extends MvpPresenter<MainActivityView> {

    private static final int CREATE_TASK = 0;
    private static final int CREATE_PROJECT = 1;

    private boolean mMenuIsOpened = false;

    public MainActivityPresenter() {
    }

    public void onFabClicked() {
        if(mMenuIsOpened) {
            getViewState().hideAddingDialogFragment();
            mMenuIsOpened = false;
        } else {
            getViewState().showAddingDialogFragment();
            mMenuIsOpened = true;
        }
    }

    public void onResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CREATE_TASK: {
                    if (data != null) {
                        Task task = data.getParcelableExtra(Task.class.getCanonicalName());
                        onTaskCreated(task);
                    }
                    break;
                }
                case CREATE_PROJECT: {
                    if (data != null) {
                        Project project = data.getParcelableExtra(Task.class.getCanonicalName());
                        onProjectCreated(project);
                    }
                    break;
                }
            }
        }
    }

    private void onTaskCreated(@NonNull Task task) {
        getViewState().addTaskToList(task, State.valueOf(task.getState()));
    }

    private void onProjectCreated(@NonNull Project project) {
        getViewState().checkOutToProject(project.getId());
    }
}
