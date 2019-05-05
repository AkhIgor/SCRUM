package com.igor.scrumassistant.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.data.constants.Swipe;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.adapter.interactive.ITouchHelperAdapter;
import com.igor.scrumassistant.view.fragment.ItemSwipeListener;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>
        implements ITouchHelperAdapter {

    private final ItemSwipeListener mSwipeListener;
    private List<Task> mTaskList;

    public TaskListAdapter(@NonNull ItemSwipeListener listener, @NonNull List<Task> taskList) {
        mSwipeListener = listener;
        mTaskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_list_item, viewGroup, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {
        taskViewHolder.mIdTextView.setText(String.valueOf(mTaskList.get(i).getId()));
        taskViewHolder.mDescriptionTextView.setText(mTaskList.get(i).getPurpose());
        taskViewHolder.mAuthorTextView.setText(mTaskList.get(i).getCreatorName());
        setPriority(taskViewHolder.mPriorityTextView, Priority.valueOf(mTaskList.get(i).getPriority()));
        if (mTaskList.get(i).getExecutorId() != -1) {
            taskViewHolder.mExecutorTextView.setText(mTaskList.get(i).getExecutorName());
        }
    }

    @Override
    public int getItemCount() {
        return mTaskList.size();
    }

    public void updateList(@NonNull List<Task> tasks) {
        mTaskList = tasks;
        notifyDataSetChanged();
    }

    private void setPriority(@NonNull TextView priorityText, @NonNull Priority priority) {
        switch (priority) {
            case LOW:
                priorityText.setText(R.string.low_status);
                priorityText.setTextColor(priorityText.getResources().getColor(R.color.low_status_color));
                break;
            case MEDIUM:
                priorityText.setText(R.string.medium_status);
                priorityText.setTextColor(priorityText.getResources().getColor(R.color.medium_status_color));
                break;
            case HIGH:
                priorityText.setText(R.string.high_status);
                priorityText.setTextColor(priorityText.getResources().getColor(R.color.high_status_color));
                break;
            case CRITICAL:
                priorityText.setText(R.string.critical_status);
                priorityText.setTextColor(priorityText.getResources().getColor(R.color.critical_status_color));
                break;
        }
    }

    @Override
    public void onItemSwipe(@NonNull Swipe swipe, int position) {
        mSwipeListener.onItemSwipe(swipe, position);
        mTaskList.remove(position);
        notifyItemRemoved(position);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private final TextView mIdTextView;
        private final TextView mPriorityTextView;
        private final TextView mDescriptionTextView;
        private final TextView mExecutorTextView;
        private final TextView mAuthorTextView;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            mIdTextView = itemView.findViewById(R.id.task_id_text_view);
            mPriorityTextView = itemView.findViewById(R.id.task_priority_text_view);
            mDescriptionTextView = itemView.findViewById(R.id.task_description_text_view);
            mExecutorTextView = itemView.findViewById(R.id.task_executor_text_view);
            mAuthorTextView = itemView.findViewById(R.id.task_creator_text_view);
        }
    }
}
