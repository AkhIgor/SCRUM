package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

public interface CommonSceneFragmentView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setTaskList(@NonNull List<Task> taskList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void updateTaskList(@NonNull List<Task> taskList);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void addTaskToList(@NonNull Task task);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void removeTaskFromList(int position);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void notifyTaskPriorityChanged(@NonNull Priority newPriority, int position);
}
