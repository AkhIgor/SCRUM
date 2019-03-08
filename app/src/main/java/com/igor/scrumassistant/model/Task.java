package com.igor.scrumassistant.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.data.constants.State;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mPurpose;
    private State mState;
    private Priority mPriority;
    private long mExecutorId;
    private long mProjectId;
    private long mCreatorId;

    public Task(String purpose, long executorId, long projectId, long creatorId) {
        this.mPurpose = purpose;
        this.mExecutorId = executorId;
        this.mProjectId = projectId;
        this.mCreatorId = creatorId;
    }

    public Task(String purpose, long projectId, long creatorId) {
        this.mPurpose = purpose;
        this.mProjectId = projectId;
        this.mCreatorId = creatorId;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getPurpose() {
        return mPurpose;
    }

    public void setPurpose(String mPurpose) {
        this.mPurpose = mPurpose;
    }

    public State getState() {
        return mState;
    }

    public void setState(State mState) {
        this.mState = mState;
    }

    public long getExecutorId() {
        return mExecutorId;
    }

    public void setExecutorId(long executorId) {
        this.mExecutorId = executorId;
    }

    public long getProjectId() {
        return mProjectId;
    }

    public void setProjectId(long projectId) {
        this.mProjectId = projectId;
    }

    public long getCreatorId() {
        return mCreatorId;
    }

    public void setCreatorId(long creatorId) {
        this.mCreatorId = creatorId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Task executor = (Task) obj;
        return (executor.getPurpose().equals(this.mPurpose) &&
                executor.getState().equals(this.mState) &&
                executor.getExecutorId() == this.mExecutorId &&
                executor.getProjectId() == this.mProjectId &&
                executor.getCreatorId() == this.mCreatorId);
    }

    @Override
    public int hashCode() {
        return (mPurpose.hashCode() +
                mState.hashCode());
    }
}
