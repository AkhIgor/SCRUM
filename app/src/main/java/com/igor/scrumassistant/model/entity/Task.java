package com.igor.scrumassistant.model.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.data.constants.State;

@Entity
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long mId;
    private String mPurpose;
    private State mState;
    private Priority mPriority;
    private long mExecutorId;
    private String mExecutorName;
    private long mProjectId;
    private long mCreatorId;
    private String mCreatorName;

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

    protected Task(Parcel in) {
        mId = in.readLong();
        mPurpose = in.readString();
        mExecutorId = in.readLong();
        mExecutorName = in.readString();
        mProjectId = in.readLong();
        mCreatorId = in.readLong();
        mCreatorName = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

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

    public Priority getPriority() {
        return mPriority;
    }

    public void setPriority(Priority mPriority) {
        this.mPriority = mPriority;
    }

    public String getExecutorName() {
        return mExecutorName;
    }

    public void setExecutorName(String mExecutorName) {
        this.mExecutorName = mExecutorName;
    }

    public String getCreatorName() {
        return mCreatorName;
    }

    public void setCreatorName(String mCreatorName) {
        this.mCreatorName = mCreatorName;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mId);
        dest.writeString(mPurpose);
        dest.writeLong(mExecutorId);
        dest.writeString(mExecutorName);
        dest.writeLong(mProjectId);
        dest.writeLong(mCreatorId);
        dest.writeString(mCreatorName);
    }
}
