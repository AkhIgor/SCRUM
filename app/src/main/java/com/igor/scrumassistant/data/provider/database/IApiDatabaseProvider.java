package com.igor.scrumassistant.data.provider.database;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

public interface IApiDatabaseProvider {

    List<Executor> getAllExecutors();

    List<Executor> getExecutorInCurrProject();

    Executor getExecutorById(long id);

    List<Task> getAllTasks();

    Task getTaskById(long id);

    List<Task> getOpenTasks();

    List<Task> getInWorkTasks();

    List<Task> getDoneTasks();

    List<Task> getDeclinedTasks();

    List<Project> getAllProjects();

    void addTask(@NonNull Task task);

    void addProject(@NonNull Project project);
}
