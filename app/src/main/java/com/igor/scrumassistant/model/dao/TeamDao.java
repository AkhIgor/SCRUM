package com.igor.scrumassistant.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTeam(@NonNull Team team);

    @Delete
    void deleteTeam(@NonNull Team team);

    @Update
    void updateTeam(@NonNull Team team);

    @Query("SELECT * FROM Team")
    List<Team> getAllTeams();

    @Query("SELECT mParticipantId FROM Team WHERE mProjectId = :projectId")
    List<Long> getParticipantsByProject(long projectId);

    @Query("SELECT mProjectId FROM Team WHERE mParticipantId = :participantId")
    List<Long> getProjectsByParticipants(long participantId);
}
