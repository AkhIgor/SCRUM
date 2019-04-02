package com.igor.scrumassistant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.presentation.activity.CreatingActivityPresenter;
import com.igor.scrumassistant.view.CreatingActivityView;
import com.igor.scrumassistant.view.adapter.ExecutorListAdapter;

import java.util.List;

public class CreatingActivity extends AppCompatActivity implements CreatingActivityView {

    private EditText mDescriptionText;
    private FloatingActionButton mSaveButton;
    private TextView mPriorityTextView;
    private RecyclerView mExecutorListView;
    private ProgressBar mProgressBar;
    private ExecutorListAdapter mAdapter;

    private List<Executor> mExecutorList;

    private Task newTask;

    @InjectPresenter
    CreatingActivityPresenter mPresenter;

    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, CreatingActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }

    @ProvidePresenter
    CreatingActivityPresenter providePresenter() {
        return new CreatingActivityPresenter();
    }

    private void initViews() {
        mDescriptionText = findViewById(R.id.creating_activity_description_text_view);
        mSaveButton = findViewById(R.id.fab);
        mPriorityTextView = findViewById(R.id.creating_activity_priority_text_view);
        mExecutorListView = findViewById(R.id.executor_list_view);
        mExecutorListView.setVisibility(View.INVISIBLE);
        mProgressBar = findViewById(R.id.executor_loading_progress_bar);

        mSaveButton.setOnClickListener(v -> mPresenter.onSaveButtonClicked());
    }

    public void setListEnabled(@NonNull List<Executor> executorList) {
        mExecutorList = executorList;
        mAdapter = new ExecutorListAdapter(mExecutorList);
        mExecutorListView.setAdapter(mAdapter);
        mProgressBar.setVisibility(View.GONE);
        mExecutorListView.setVisibility(View.VISIBLE);
    }

    @Override
    public void addTaskToList() {
        createTask();
        addTaskToDescBoard();
    }

    private void addTaskToDescBoard() {
        setResult(RESULT_OK);
        getIntent().putExtra(Task.class.getCanonicalName(), newTask);
        mPresenter.addToServer(newTask);
        finish();
    }

    private void createTask() {
        String description = mDescriptionText.getText().toString();
        long creatorId = CurrentUser.getUserId();
        long projectId = CurrentUser.getProjectId();
        long executorID = mExecutorList.get(mAdapter.getChosenPosition()).getId();
        if (executorID != -1) {
            newTask = new Task(description, executorID, projectId, creatorId);
        } else {
            newTask = new Task(description, projectId, creatorId);
        }
        newTask.setPriority(Priority.getEnum(mPriorityTextView.getText().toString()));
    }
}
