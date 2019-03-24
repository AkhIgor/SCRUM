package com.igor.scrumassistant.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.ref.WeakReference;

public abstract class ViewHolderWrapper implements View.OnClickListener {

    protected final WeakReference<RecyclerView.ViewHolder> mWrappedHolderRef;

    protected ViewHolderWrapper(RecyclerView.ViewHolder holder)
    {
        mWrappedHolderRef = new WeakReference<>(holder);
    }
}
