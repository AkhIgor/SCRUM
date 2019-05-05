package com.igor.scrumassistant.data.provider.database;

import android.content.Context;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class ApiDatabaseProvider implements IApiDatabaseProvider {

    private final Database mDatabase;
    private Context mContext;

    public ApiDatabaseProvider(@NonNull Context context) {
        mDatabase = Database.initDataBase(context);
    }

    @Override
    public List<Executor> getAllExecutors() {
        //если данные изменились - то они приходят в accept, который что-то с ними делает.
        //Ориентировочно уведомляет подписчиков
        //На данный момент нет необходимости в таком функционале, так как с БД устройства работает лишь один пользователь

//        mDatabase.executorDao().getAllTasks()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<List<Executor>>() {
//                    @Override
//                    public void accept(List<Executor> executors) throws Exception {
//
//                    }
//                });
        return mDatabase.executorDao().getAll();
    }

    @Override
    public List<Executor> getExecutorInCurrProject() {
        List<Long> participantIds = mDatabase.teamDao().getParticipantsByProject(CurrentUser.getProjectId(mContext));
        List<Executor> executors = new ArrayList<>();
        if(participantIds != null) {
            Executor executor;
            for(int i = 0; i < participantIds.size(); i++) {
                executor = getExecutorById(participantIds.get(i));
                if(executor != null) {
                    executors.add(executor);
                }
            }
        }
        return executors;
    }

    @Override
    public Executor getExecutorById(long id) {
        return mDatabase.executorDao().getExecutorById(id);
    }

    @Override
    public List<Task> getOpenTasks() {
        return mDatabase.taskDao().getTasksBySate(State.OPEN.toString());
    }

    @Override
    public List<Task> getInWorkTasks() {
        return mDatabase.taskDao().getTasksBySate(State.IN_WORK.toString());
    }

    @Override
    public List<Task> getDoneTasks() {
        return mDatabase.taskDao().getTasksBySate(State.DONE.toString());
    }

    @Override
    public List<Task> getDeclinedTasks() {
        return mDatabase.taskDao().getTasksBySate(State.DECLINED.toString());
    }

    @Override
    public List<Task> getAllTasks() {
        return mDatabase.taskDao().getAllTasks();
    }

    @Override
    public Task getTaskById(long id) {
        return null;
    }

    @Override
    public List<Project> getAllProjects() {
        return mDatabase.projectDao().getAll();
    }

    @Override
    public void addTask(@NonNull Task task) {
        new Thread(() -> mDatabase.taskDao().addTask(task));
    }

    @Override
    public void addProject(@NonNull Project project) {
        mDatabase.projectDao().addProject(project);
    }
}
