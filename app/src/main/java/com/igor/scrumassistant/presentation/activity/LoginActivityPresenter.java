package com.igor.scrumassistant.presentation.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.view.LoginActivityView;

import java.lang.ref.WeakReference;
import java.util.IllegalFormatConversionException;

@InjectViewState
public class LoginActivityPresenter extends MvpPresenter<LoginActivityView> {

    private static final int USER_FOUND = 0;
    private static final int USER_NOT_FOUND = 1;

    private final Context mContext;
    private Database mDatabase;
    private final LoginHandler mHandler;

    public LoginActivityPresenter(@NonNull Context context) {
        mContext = context;
        mDatabase = Database.initDataBase(context);
        mHandler = new LoginHandler(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        if (CurrentUser.getUserId(mContext) != -1) {
            getViewState().setLogin(Long.toString(CurrentUser.getUserId(mContext)));
        }
    }

    public void onSignButtonPressed(@NonNull String login, @NonNull String password) {
        getViewState().showProgressBar();
        new Thread(() -> {
            try {
                long id = Long.parseLong(login);
                Executor user = mDatabase.executorDao().getExecutorById(id);
                if (user != null && (user.getPassword().equals(password))) {
                    mHandler.sendEmptyMessage(USER_FOUND);
                } else {
                    mHandler.sendEmptyMessage(USER_NOT_FOUND);
                }
            } catch (NumberFormatException exception) {
                mHandler.sendEmptyMessage(USER_NOT_FOUND);
            }
        }).start();
    }

    private void showErrorMessage() {
        getViewState().hideProgressBar();
        getViewState().showError();
    }

    private void signIn() {
        getViewState().hideProgressBar();
        getViewState().setUserId();

        if(CurrentUser.getProjectId(mContext) == -1) {
            getViewState().openProjectsList();
        } else {
            getViewState().openTasks();
        }
    }

    private static class LoginHandler extends Handler {

        private final WeakReference<LoginActivityPresenter> mPresenterRef;

        LoginHandler(@NonNull LoginActivityPresenter presenter) {
            mPresenterRef = new WeakReference<>(presenter);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case USER_FOUND: {
                    mPresenterRef.get().signIn();
                    break;
                }
                case USER_NOT_FOUND: {
                    mPresenterRef.get().showErrorMessage();
                    break;
                }
            }
        }
    }
}
