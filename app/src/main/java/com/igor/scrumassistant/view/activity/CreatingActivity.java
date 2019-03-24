package com.igor.scrumassistant.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.presentation.CreatingActivityPresenter;
import com.igor.scrumassistant.view.CreatingActivityView;
import com.igor.scrumassistant.view.adapter.ExecutorListAdapter;
import com.igor.scrumassistant.view.adapter.SelectionHelper;
import com.igor.scrumassistant.view.adapter.SelectionObserver;

import java.util.List;

public class CreatingActivity extends AppCompatActivity implements CreatingActivityView {

    private final ActionModeCallback mActionModeCallback = new ActionModeCallback();

    private EditText mDescriptionText;
    private FloatingActionButton mSaveButton;
    private TextView mPriorityTextView;
    private RecyclerView mExecutorListView;
    private ProgressBar mProgressBar;
    private ExecutorListAdapter mAdapter;

    private List<Executor> mExecutorList;

    @InjectPresenter
    CreatingActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initViews();
    }


    public void startActionMode() {
        startActionMode(mActionModeCallback);
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
        mProgressBar.setVisibility(View.GONE);
        mExecutorListView.setVisibility(View.VISIBLE);
        mAdapter = new ExecutorListAdapter(executorList, this);
        mExecutorListView.setAdapter(mAdapter);
    }

    @Override
    public void createTask() {
        String description = mDescriptionText.getText().toString();
        long creatorId = CurrentUser.getUserId();
        long projectId = CurrentUser.getProjectId();

        Task newTask;
    }


    private class ActionModeCallback implements ActionMode.Callback, SelectionObserver {
        private ActionMode mActionMode;

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            SelectionHelper selectionHelper = mAdapter.getSelectionHelper();
            selectionHelper.unregisterSelectionObserver(this);
            mActionMode = null;
            selectionHelper.setSelectable(false);
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            mActionMode = actionMode;
//            mActionMode.getMenuInflater().inflate(R.menu.gallery_selection, menu);
            mAdapter.getSelectionHelper().registerSelectionObserver(this);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
//            switch (menuItem.getItemId()) {
//                case R.id.menu_toast:
                    Toast.makeText(CreatingActivity.this,
                            R.string.item_selected, Toast.LENGTH_SHORT).show();
//                    break;
//            }
            return true;
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder holder, boolean isSelected) {
            if (mActionMode != null) {
                int checkedImagesCount = mAdapter.getSelectionHelper().getSelectedItemsCount();
                mActionMode.setTitle(String.valueOf(checkedImagesCount));
            }
        }

        @Override
        public void onSelectableChanged(boolean isSelectable) {
            if (!isSelectable) {
                mActionMode.finish();
            }
        }
    }
}
