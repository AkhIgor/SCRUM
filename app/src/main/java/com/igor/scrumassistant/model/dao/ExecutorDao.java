package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Name;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ExecutorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addExecutor(@NonNull Executor executor);

    @Delete
    void deleteExecutor(@NonNull Executor executor);

    @Update
    void updateExecutor(@NonNull Executor executor);

    //    Flowable<List<Executor>> getAllTasks(); - RxJava
    @Query("SELECT * FROM Executor")
    List<Executor> getAll();

    @Query("SELECT * FROM Executor WHERE mId = :id")
    Executor getExecutorById(long id);

    @Query("SELECT mName, mSurname FROM Executor WHERE mId = :id")
    Name getNameById(long id);
}
