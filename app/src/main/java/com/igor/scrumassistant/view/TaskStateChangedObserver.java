package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Task;

public interface TaskStateChangedObserver {

    void notifyTaskStateChanged(@NonNull State state, @NonNull Task task);
}
