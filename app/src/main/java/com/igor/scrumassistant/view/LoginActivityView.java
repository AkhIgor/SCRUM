package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;

public interface LoginActivityView extends MvpView {

    void showProgressBar();

    void hideProgressBar();

    void setLogin(@NonNull String login);

    void showError();

    void openTasks();

    void openProjectsList();

    void setUserId();
}
