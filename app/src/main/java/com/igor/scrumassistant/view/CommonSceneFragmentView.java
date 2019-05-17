package com.igor.scrumassistant.view;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

public interface CommonSceneFragmentView extends MvpView {

    @StateStrategyType(AddToEndStrategy.class)
    void setTaskList(@NonNull List<Task> taskList);

    @StateStrategyType(AddToEndStrategy.class)
    void updateTaskList(@NonNull List<Task> taskList);

    @StateStrategyType(AddToEndStrategy.class)
    void addTaskToList();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void cancelRemovement();

    @StateStrategyType(AddToEndStrategy.class)
    void removeTaskFromList(int position);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void notifyTaskPriorityChanged(@NonNull State state, @NonNull Task task);

    @StateStrategyType(AddToEndStrategy.class)
    void checkOut();
}
