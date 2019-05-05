package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.Project;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addProject(@NonNull Project project);

    @Delete
    void deleteProject(@NonNull Project project);

    @Update
    void updateProject(@NonNull Project project);

    @Query("SELECT * FROM Project")
    List<Project> getAll();

    @Query("SELECT * FROM Project WHERE mId = :id")
    Project getProjectById(long id);

    @Query("SELECT mId FROM Project WHERE mName = :projectName")
    Long getProjectIdByName(String projectName);
}
