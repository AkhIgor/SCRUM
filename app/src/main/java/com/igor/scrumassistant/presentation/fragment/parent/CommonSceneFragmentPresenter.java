package com.igor.scrumassistant.presentation.fragment.parent;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.data.constants.Swipe;
import com.igor.scrumassistant.data.provider.CommonDataProvider;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.view.CommonSceneFragmentView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@InjectViewState
public abstract class CommonSceneFragmentPresenter extends MvpPresenter<CommonSceneFragmentView>
        implements Customer<Task> {

    protected static final int LIST_READ = 0;

    protected Context mContext;
    protected TaskListInitializer mHandler;
    protected CommonDataProvider mProvider;
    protected List<Task> mTaskList = new ArrayList<>();
    private LoaderManager mLoaderManager;

    public CommonSceneFragmentPresenter(@NonNull Context context, @NonNull LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
        mHandler = new TaskListInitializer(this);
    }

    //родительский класс
    public void startFragment() {
        getList();
    }

    //Retrofit
    protected abstract void getList();
    //Запрос к стороннему классу, который запрашивает через Retrofit

    public abstract void onTaskSwiped(@NonNull Swipe swipe, int position);

    public void onAddChangedTaskStateToList(@NonNull Task task) {
        mTaskList.add(0, task);
        getViewState().addTaskToList(task);
    }

    @Override
    public void setList(@NonNull List list) {
        mTaskList = (List<Task>) list;
        initList();
    }

    protected void changeTaskState(@NonNull State state, int taskPosition) {
        Task task = mTaskList.get(taskPosition);
        mTaskList.remove(taskPosition);
        task.setState(state);
        getViewState().removeTaskFromList(taskPosition);
        getViewState().notifyTaskPriorityChanged(state, task);
    }

    private void initList() {
        getViewState().setTaskList(mTaskList);
    }

    protected static class TaskListInitializer extends Handler {

        private final WeakReference<CommonSceneFragmentPresenter> mPresenterRef;

        TaskListInitializer(@NonNull CommonSceneFragmentPresenter presenter) {
            mPresenterRef = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == LIST_READ) {
                mPresenterRef.get().initList();
            }
        }
    }
}
