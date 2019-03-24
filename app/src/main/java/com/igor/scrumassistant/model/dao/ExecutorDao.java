package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Name;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ExecutorDao {

    @Query("SELECT * FROM Executor")
    Flowable<List<Executor>> getAll();

    @Query("SELECT mName, mSurname FROM Executor WHERE mId = :id")
    Flowable<Name> getNameById(long id);
}
