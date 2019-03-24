package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    Flowable<List<Task>> getAll();
}
