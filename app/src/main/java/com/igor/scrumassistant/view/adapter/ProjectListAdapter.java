package com.igor.scrumassistant.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.igor.scrumassistant.R;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.view.IProjectOpener;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {

    private List<Project> mProjectList;
    private IProjectOpener mProjectOpener;

    public ProjectListAdapter(@NonNull List<Project> projectList, @NonNull IProjectOpener projectOpener) {
        mProjectList = projectList;
        mProjectOpener = projectOpener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.project_item, viewGroup, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder projectViewHolder, int i) {
        projectViewHolder.mProjectNameTextView.setText(mProjectList.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder {

        private TextView mProjectNameTextView;

        ProjectViewHolder(@NonNull View itemView) {
            super(itemView);

            mProjectNameTextView = itemView.findViewById(R.id.project_name);

            itemView.setOnClickListener(v -> mProjectOpener.openProject(mProjectList.get(getAdapterPosition())));
        }
    }
}
