package com.igor.scrumassistant.data.provider;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;
import java.util.Observable;

import io.reactivex.Flowable;

public interface IApiDataProvider {

    void getAllExecutors();

    void addProject(@NonNull Project project);

    void getExecutorsInTheProject(@NonNull Customer customer);

    void getAllTasks();

    void getTaskById(@NonNull Customer customer, long id);

    void getOpenTasks(@NonNull Customer customer);

    void getInWorkTasks(@NonNull Customer customer);

    void getDoneTasks(@NonNull Customer customer);

    void getDeclinedTasks(@NonNull Customer customer);

    void getAllProjects();

    void addTask(@NonNull Task task);
}
