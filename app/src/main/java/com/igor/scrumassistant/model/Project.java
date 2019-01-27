package com.igor.scrumassistant.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.util.List;

@Entity
public class Project {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    @Ignore
    private List<Long> participantsId;

    public Project(String name) {
        this.name = name;
    }

    public Project() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(List<Long> participantsId) {
        this.participantsId = participantsId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Project executor = (Project) obj;
        return (executor.getName().equals(this.name));
    }

    @Override
    public int hashCode() {
        return (name.hashCode());
    }
}
