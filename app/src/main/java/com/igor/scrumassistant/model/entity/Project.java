package com.igor.scrumassistant.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.List;

@Entity
public class Project implements AppEntity, Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mName;
    private long mAuthorId;
    @Ignore
    private List<Long> mParticipantsId;

    public Project() {
    }

    public Project(String name) {
        this.mName = name;
    }

    protected Project(Parcel in) {
        mId = in.readLong();
        mName = in.readString();
        mAuthorId = in.readLong();
    }

    public static final Creator<Project> CREATOR = new Creator<Project>() {
        @Override
        public Project createFromParcel(Parcel in) {
            return new Project(in);
        }

        @Override
        public Project[] newArray(int size) {
            return new Project[size];
        }
    };

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

    public long getAuthorId() {
        return mAuthorId;
    }

    public void setAuthorId(long mAuthorId) {
        this.mAuthorId = mAuthorId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mName);
        dest.writeLong(mAuthorId);
    }
}
