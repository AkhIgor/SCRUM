package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.igor.scrumassistant.model.entity.Executor;

import java.util.List;

public interface ProjectCreatingActivityView extends MvpView {

    void checkOut();

    void setListEnabled(@NonNull List<Executor> executorList);
}
