package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.igor.scrumassistant.model.entity.Project;

import java.util.List;

public interface ChoosingProjectActivityView extends MvpView {

    void setList(@NonNull List<Project> projects);

    void createProject();

    void addProjectToList(@NonNull Project project);
}
