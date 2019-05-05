package com.igor.scrumassistant.data.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.dao.ExecutorDao;
import com.igor.scrumassistant.model.dao.ProjectDao;
import com.igor.scrumassistant.model.dao.TaskDao;
import com.igor.scrumassistant.model.dao.TeamDao;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.model.entity.Team;

@android.arch.persistence.room.Database(entities = {Task.class, Executor.class, Project.class, Team.class}, version = 1)
public abstract class Database extends RoomDatabase {

    public static Database initDataBase(@NonNull Context context) {
        return Room.databaseBuilder(context,
                Database.class,
                "Scrum Assistant Database.db")
                .build();
    }

    public abstract TaskDao taskDao();

    public abstract ExecutorDao executorDao();

    public abstract ProjectDao projectDao();

    public abstract TeamDao teamDao();
}
