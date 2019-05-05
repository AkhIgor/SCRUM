package com.igor.scrumassistant.view.fragment.parent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.data.constants.Swipe;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.presentation.fragment.parent.CommonSceneFragmentPresenter;
import com.igor.scrumassistant.view.CommonSceneFragmentView;
import com.igor.scrumassistant.view.TaskStateChangedObserver;
import com.igor.scrumassistant.view.TaskStateChangedSubscriber;
import com.igor.scrumassistant.view.adapter.TaskListAdapter;
import com.igor.scrumassistant.view.fragment.ItemSwipeListener;
import com.igor.scrumassistant.view.fragment.arello.MvpFragment;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

public abstract class SceneFragment extends MvpFragment
        implements CommonSceneFragmentView, TaskStateChangedSubscriber {

    private TaskStateChangedObserver mObserver;
    private RecyclerView mRecyclerView;
    private TaskListAdapter mAdapter;
    private ProgressBar mProgressBar;
    private List<Task> mTaskList = new LinkedList<>();

    @InjectPresenter
    CommonSceneFragmentPresenter mPresenter;

    @ProvidePresenter
    protected abstract CommonSceneFragmentPresenter providePresenter();

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
    public void onStart() {
        super.onStart();
        mPresenter.startFragment();
    }

    //вызов метода из Handler
    public void setTaskList(@NonNull List<Task> taskList) {
        mTaskList = taskList;
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.updateList(taskList);
    }

    @Override
    public void updateTaskList(@NonNull List<Task> taskList) {
        mTaskList.clear();
        mTaskList.addAll(taskList);
        mAdapter.updateList(taskList);
//        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void addTaskToList(@NonNull Task task) {
        mTaskList.add(0, task);
        mAdapter.notifyItemInserted(0);
        mPresenter.onTaskAdded(task);
    }

    @Override
    public void removeTaskFromList(int position) {
        mTaskList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void notifyTaskPriorityChanged(@NonNull State state, @NonNull Task task) {
        mObserver.notifyTaskStateChanged(state, task);
    }

    @Override
    public void addChangedTaskStateToList(@NonNull Task task) {
        mPresenter.onAddChangedTaskStateToList(task);
    }

    @Override
    public void checkOut() {
//        mPresenter.initLoader();
    }

    //Вызывается в onViewCreated
    private void initViews(@NonNull View view) {
        mObserver = (TaskStateChangedObserver) getActivity();
        mRecyclerView = view.findViewById(R.id.common_scene_recycler_view);
        mProgressBar = view.findViewById(R.id.common_scene_progress_bar);
        mAdapter = new TaskListAdapter(new ItemSwipeListenerImpl(this), mTaskList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private static class ItemSwipeListenerImpl implements ItemSwipeListener {

        private final WeakReference<SceneFragment> mFragmentRef;

        private ItemSwipeListenerImpl(@NonNull SceneFragment fragment) {
            mFragmentRef = new WeakReference<>(fragment);
        }

        @Override
        public void onItemSwipe(@NonNull Swipe swipe, int position) {
            mFragmentRef.get().mPresenter.onTaskSwiped(swipe, position);
        }
    }
}
