package com.igor.scrumassistant.data.provider;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.model.entity.AppEntity;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public interface DataObserver {

    void onListSuccess(@NonNull List entities);

    void onEntitySuccess(@NonNull AppEntity entity);

    //  Fails

    void failOnExecutorsInTheProject(@Nullable Throwable exception);

    void failOnTaskById(@Nullable Throwable exception, long id);

    void failOnOpenTasks(@Nullable Throwable exception);

    void failOnInWorkTasks(@Nullable Throwable exception);

    void failOnDoneTasks(@Nullable Throwable exception);

    void failOnDeclinedTasks(@Nullable Throwable exception);
}
