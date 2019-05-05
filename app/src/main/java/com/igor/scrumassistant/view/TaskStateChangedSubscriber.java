package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.Task;

public interface TaskStateChangedSubscriber {

    void addChangedTaskStateToList(@NonNull Task task);
}
