package com.igor.scrumassistant.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.util.List;

@Entity
public class Project {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mName;
    private long mAuthorId;
    @Ignore
    private List<Long> mParticipantsId;

    public Project(String name) {
        this.mName = name;
    }

    public Project() {
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public List<Long> getParticipantsId() {
        return mParticipantsId;
    }

    public void setParticipantsId(List<Long> participantsId) {
        this.mParticipantsId = participantsId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Project executor = (Project) obj;
        return (executor.getName().equals(this.mName));
    }

    @Override
    public int hashCode() {
        return (mName.hashCode());
    }
}
