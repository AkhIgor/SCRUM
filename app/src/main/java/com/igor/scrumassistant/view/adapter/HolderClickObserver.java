package com.igor.scrumassistant.view.adapter;

import android.support.v7.widget.RecyclerView;

public interface HolderClickObserver
{
    void onHolderClick(RecyclerView.ViewHolder holder);

    boolean onHolderLongClick(RecyclerView.ViewHolder holder);
}


