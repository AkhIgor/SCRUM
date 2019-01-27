package com.igor.scrumassistant.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
