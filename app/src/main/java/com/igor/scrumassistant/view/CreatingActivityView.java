package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.igor.scrumassistant.model.entity.Executor;

import java.util.List;

public interface CreatingActivityView extends MvpView {

    void setListEnabled(@NonNull List<Executor> executorList);

    void createTask();
}
