package com.igor.scrumassistant.view.adapter.interactive;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.igor.scrumassistant.data.constants.Swipe;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_SWIPE;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ITouchHelperAdapter adapter;
    private Swipe mSwipe;

    public SimpleItemTouchHelperCallback(@NonNull ITouchHelperAdapter itemTouchHelperAdapter) {
        this.adapter = itemTouchHelperAdapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeFlag(ACTION_STATE_SWIPE, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        if(dX > 0) {
            mSwipe = Swipe.RIGHT;
        } else {
            mSwipe = Swipe.LEFT;
        }
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        adapter.onItemSwipe(mSwipe, viewHolder.getAdapterPosition());
    }
}
