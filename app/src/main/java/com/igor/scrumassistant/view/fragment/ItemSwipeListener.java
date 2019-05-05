package com.igor.scrumassistant.view.fragment;

import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.Swipe;

public interface ItemSwipeListener {

    void onItemSwipe(@NonNull Swipe swipe, int position);
}
