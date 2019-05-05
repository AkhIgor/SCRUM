package com.igor.scrumassistant.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import com.igor.scrumassistant.data.constants.Role;

@Entity
public class Executor implements AppEntity {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mName;
    private String mSurname;
    private String mRole;
    private String mPassword;
    @Ignore
    private boolean mIsChosen = false;

    public Executor() {
    }

    public Executor(String name, String surname, Role role) {
        this.mName = name;
        this.mSurname = surname;
        this.mRole = role.toString();
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

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        this.mSurname = surname;
    }

    public String getRole() {
        return mRole;
    }

    public void setRole(Role role) {
        this.mRole = role.toString();
    }

    public void setRole(String role) {
        this.mRole = role;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this)
            return true;
        else if (obj == null || obj.getClass() != this.getClass())
            return false;

        Executor executor = (Executor) obj;
        return (executor.getName().equals(this.mName) &&
                executor.getSurname().equals(this.mSurname) &&
                executor.getRole().equals(this.mRole));
    }

    @Override
    public int hashCode() {
        return (mName.hashCode() +
                mSurname.hashCode() +
                mRole.hashCode());
    }

    public boolean isChosen() {
        return mIsChosen;
    }

    public void setChosen(boolean chosen) {
        mIsChosen = chosen;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }
}
