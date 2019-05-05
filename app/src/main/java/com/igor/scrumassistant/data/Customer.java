package com.igor.scrumassistant.data;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.model.entity.AppEntity;

import java.util.List;

public interface Customer<T> {

    void setEntity(@NonNull AppEntity entity);

    void setList(@NonNull List list);
}
