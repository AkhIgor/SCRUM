package com.igor.scrumassistant.presentation.activity;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.CreatingActivityView;

import java.util.ArrayList;

@InjectViewState
public class CreatingActivityPresenter extends MvpPresenter<CreatingActivityView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        //запрос на пользователей
        getViewState().setListEnabled(new ArrayList<Executor>());
    }

    public void onSaveButtonClicked() {
        getViewState().addTaskToList();
    }

    public void addToServer(@NonNull Task task) {

    }
}
