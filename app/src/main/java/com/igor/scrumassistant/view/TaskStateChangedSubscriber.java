package com.igor.scrumassistant.view;

public interface TaskStateChangedSubscriber {

    void addChangedTaskStateToList(int changedTaskPosition);
}
