package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.Priority;

public interface TaskStateChangedObserver {

    void notifyTaskStateChanged(@NonNull Priority priority, int changedTaskPosition);
}
