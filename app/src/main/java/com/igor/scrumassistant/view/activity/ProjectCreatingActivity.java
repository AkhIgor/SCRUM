package com.igor.scrumassistant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.presentation.activity.ProjectCreatingPresenter;
import com.igor.scrumassistant.view.IParicipantSelecter;
import com.igor.scrumassistant.view.ProjectCreatingActivityView;
import com.igor.scrumassistant.view.activity.arello.MvpAppCompatActivity;
import com.igor.scrumassistant.view.adapter.ParticipantsListAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

public class ProjectCreatingActivity extends MvpAppCompatActivity implements ProjectCreatingActivityView {

    private TextView mProjectName;
    private ParticipantsListAdapter mAdapter;
    private RecyclerView mProjectListView;
    private FloatingActionButton mCheckOutButton;
    private List<Executor> mExecutorList;
    private ProgressBar mProgressBar;

    private Project mProject;

    @InjectPresenter
    ProjectCreatingPresenter mPresenter;

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, ProjectCreatingActivity.class);
    }

    @ProvidePresenter
    ProjectCreatingPresenter providePresenter() {
        return new ProjectCreatingPresenter(this, getSupportLoaderManager());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_creating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    @Override
    public void checkOut() {
        mProject = new Project(mProjectName.getText().toString());
        mProject.setAuthorId(CurrentUser.getUserId(this));

        mPresenter.addProject(mProject);

        Intent intent = getIntent().putExtra(Project.class.getCanonicalName(), mProject);
        setResult(RESULT_OK,intent);

        finish();
    }

    @Override
    public void setListEnabled(@NonNull List<Executor> executorList) {
        mExecutorList = executorList;
        mAdapter = new ParticipantsListAdapter(mExecutorList, new ParticipantsSelecter(this));
        mProjectListView.setAdapter(mAdapter);
        mProgressBar.setVisibility(View.GONE);
        mProjectListView.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        mProjectName = findViewById(R.id.project_creating_activity_text_view);
        mCheckOutButton = findViewById(R.id.fab);
        mProgressBar = findViewById(R.id.project_creating_progress_bar);
        mCheckOutButton.setOnClickListener(v -> mPresenter.onCheckOutButtonClicked());
        mProjectListView = findViewById(R.id.participants_list_view);
    }

    private void selectItem(int position) {
        Executor executor = mExecutorList.get(position);
        if (executor.isChosen()) {
            executor.setChosen(false);
        } else {
            executor.setChosen(true);
        }
        mAdapter.notifyItemChanged(position);
    }

    private static class ParticipantsSelecter implements IParicipantSelecter {

        private final WeakReference<ProjectCreatingActivity> mActivityRef;

        ParticipantsSelecter(@NonNull ProjectCreatingActivity activity) {
            mActivityRef = new WeakReference<>(activity);
        }

        @Override
        public void participantSelected(int position) {
            mActivityRef.get().selectItem(position);
        }
    }
}
