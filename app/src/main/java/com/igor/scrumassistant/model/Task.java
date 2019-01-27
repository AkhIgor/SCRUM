package com.igor.scrumassistant.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String purpose;
    private State state;
    private long executorId;
    private long projectId;
    private long creatorId;

    public Task(String purpose, long executorId, long projectId, long creatorId) {
        this.purpose = purpose;
        this.executorId = executorId;
        this.projectId = projectId;
        this.creatorId = creatorId;
    }

    public Task(String purpose, long projectId, long creatorId) {
        this.purpose = purpose;
        this.projectId = projectId;
        this.creatorId = creatorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(long executorId) {
        this.executorId = executorId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Task executor = (Task) obj;
        return (executor.getPurpose().equals(this.purpose) &&
                executor.getState().equals(this.state) &&
                executor.getExecutorId() == this.executorId &&
                executor.getProjectId() == this.projectId &&
                executor.getCreatorId() == this.creatorId);
    }

    @Override
    public int hashCode() {
        return (purpose.hashCode() +
                state.hashCode());
    }
}
