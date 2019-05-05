package com.igor.scrumassistant.data.provider.server;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.model.entity.Task;

public interface IApiServerProvider {

    void getTeamOfTheProject();

    void getAllExecutors();

    void getExecutorById(long id);

    void getExecutorsInCurrProject();

    void getAllTasks();

    void getTaskById(long id);

    void getOpenTasks();

    void getInWorkTasks();

    void getDoneTasks();

    void getDeclinedTasks();

    void getAllProjects();

    void addTask(@NonNull Task task);
}
