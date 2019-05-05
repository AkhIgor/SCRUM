package com.igor.scrumassistant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.presentation.activity.ChoosingProjectActivityPresenter;
import com.igor.scrumassistant.view.ChoosingProjectActivityView;
import com.igor.scrumassistant.view.IProjectOpener;
import com.igor.scrumassistant.view.activity.arello.MvpAppCompatActivity;
import com.igor.scrumassistant.view.adapter.ProjectListAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ChoosingProjectActivity extends MvpAppCompatActivity implements ChoosingProjectActivityView {

    private static final int CREATE_PROJECT = 1;

    private List<Project> mProjectList = new ArrayList<>();
    private RecyclerView mProjectListView;
    private ProgressBar mProgressBar;
    private ProjectListAdapter mAdapter;

    @InjectPresenter
    ChoosingProjectActivityPresenter mPresenter;

    public static Intent getIntent(@NonNull Context context) {
        return new Intent(context, ChoosingProjectActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_project);

    }

    @Override
    protected void onStart() {
        super.onStart();

        initViews();
    }

    @ProvidePresenter
    ChoosingProjectActivityPresenter providePresenter() {
        return new ChoosingProjectActivityPresenter(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setList(@NonNull List<Project> projects) {
        mProjectList = projects;
        mAdapter = new ProjectListAdapter(mProjectList, new ProjectOpener(this));
        mProjectListView.setAdapter(mAdapter);
        mProgressBar.setVisibility(View.GONE);
        mProjectListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void createProject() {
        startActivityForResult(ProjectCreatingActivity.newIntent(this), CREATE_PROJECT);
    }

    @Override
    public void addProjectToList(@NonNull Project project) {
        mProjectList.add(project);
        mAdapter = new ProjectListAdapter(mProjectList, new ProjectOpener(this));
        mProjectListView.setAdapter(mAdapter);
        mProgressBar.setVisibility(View.GONE);
        mProjectListView.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        mProjectListView = findViewById(R.id.project_list_view);
        mProgressBar = findViewById(R.id.projects_progress_bar);
    }

    private void openTasksInProject(@NonNull Project project) {
        CurrentUser.setProjectId(this, project.getId());
        startActivity(MainActivity.getIntent(this));
    }

    private static class ProjectOpener implements IProjectOpener {

        private final WeakReference<ChoosingProjectActivity> mActivityRef;

        ProjectOpener(@NonNull ChoosingProjectActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        public void openProject(@NonNull Project project) {
            mActivityRef.get().openTasksInProject(project);
        }
    }
}
