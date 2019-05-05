package com.igor.scrumassistant.model.entity;

import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"mProjectId", "mParticipantId"})
public class Team implements AppEntity{

    private long mProjectId;
    private long mParticipantId;

    public Team(long mProjectId, long mParticipantId) {
        this.mProjectId = mProjectId;
        this.mParticipantId = mParticipantId;
    }

    public long getProjectId() {
        return mProjectId;
    }

    public void setProjectId(long mProjectId) {
        this.mProjectId = mProjectId;
    }

    public long getParticipantId() {
        return mParticipantId;
    }

    public void setParticipantId(long mParticipantId) {
        this.mParticipantId = mParticipantId;
    }
}
