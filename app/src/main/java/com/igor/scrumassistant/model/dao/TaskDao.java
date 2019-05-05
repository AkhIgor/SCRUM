package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTask(@NonNull Task task);

    @Delete
    void deleteTask(@NonNull Task task);

    @Update
    void updateTask(@NonNull Task task);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM Task WHERE mState = :state")
    List<Task> getTasksBySate(@NonNull String state);

    @Query("SELECT * FROM Task WHERE mId = :id")
    Task getTaskById(long id);

    @Query("SELECT * FROM Task WHERE mProjectId = :projectId AND mState = :state")
    List<Task> getTaskByStateInProject(long projectId, @NonNull String state);
}
