package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;

public interface MainActivityView extends MvpView {

    void showAddingDialogFragment();

    void hideAddingDialogFragment();

    void showAddingProjectDialogFragment();

    void hideAddingProjectDialogFragment();

    void addTaskToList(@NonNull Task task, @NonNull State state);

    void checkOutToProject(long projectId);
}
