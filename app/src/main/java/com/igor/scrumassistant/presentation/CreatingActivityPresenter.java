package com.igor.scrumassistant.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.view.CreatingActivityView;

import java.util.ArrayList;

@InjectViewState
public class CreatingActivityPresenter extends MvpPresenter<CreatingActivityView> {

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().setListEnabled(new ArrayList<Executor>());
    }

    public void onSaveButtonClicked() {

    }
}
