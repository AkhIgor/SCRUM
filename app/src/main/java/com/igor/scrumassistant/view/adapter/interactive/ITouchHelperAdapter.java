package com.igor.scrumassistant.view.adapter.interactive;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.Swipe;

public interface ITouchHelperAdapter {

    void onItemSwipe(@NonNull Swipe swipe, int position);
}
