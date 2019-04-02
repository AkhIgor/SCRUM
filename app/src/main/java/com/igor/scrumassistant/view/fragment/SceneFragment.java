package com.igor.scrumassistant.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.Priority;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.presentation.fragment.CommonSceneFragmentPresenter;
import com.igor.scrumassistant.view.CommonSceneFragmentView;
import com.igor.scrumassistant.view.TaskStateChangedObserver;
import com.igor.scrumassistant.view.TaskStateChangedSubscriber;
import com.igor.scrumassistant.view.adapter.TaskListAdapter;

import java.util.List;

public abstract class SceneFragment extends Fragment implements CommonSceneFragmentView, TaskStateChangedSubscriber {

    private TaskStateChangedObserver mObserver;
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private ProgressBar mProgressBar;
    private List<Task> mTaskList;

    @InjectPresenter
    CommonSceneFragmentPresenter mPresenter;

    @ProvidePresenter
    abstract CommonSceneFragmentPresenter providePresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_common_scene, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setTaskList(@NonNull List<Task> taskList) {
        mTaskList = taskList;
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateTaskList(@NonNull List<Task> taskList) {
        mTaskList.clear();
        mTaskList.addAll(taskList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTaskToList(@NonNull Task task) {
        mTaskList.add(0, task);
        mAdapter.notifyItemInserted(0);
    }

    @Override
    public void removeTaskFromList(int position) {
        mTaskList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void addChangedTaskStateToList(int changedTaskPosition) {
        mPresenter.onAddChangedTaskStateToList(changedTaskPosition);
    }

    @Override
    public void notifyTaskPriorityChanged(Priority newPriority, int position) {
        mObserver.notifyTaskStateChanged(newPriority, position);
    }

    private void initViews(@NonNull View view) {
        mObserver = (TaskStateChangedObserver) getActivity();
        mRecyclerView = view.findViewById(R.id.common_scene_recycler_view);
        mProgressBar = view.findViewById(R.id.common_scene_progress_bar);
        mAdapter = new TaskListAdapter(mTaskList);
        mRecyclerView.setAdapter(mAdapter);
    }
}
