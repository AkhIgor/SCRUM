package com.igor.scrumassistant.view.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.Role;
import com.igor.scrumassistant.data.database.Database;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.presentation.activity.LoginActivityPresenter;
import com.igor.scrumassistant.view.LoginActivityView;
import com.igor.scrumassistant.view.activity.arello.MvpAppCompatActivity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends MvpAppCompatActivity implements LoginActivityView {

    private EditText mLoginView;
    private EditText mPasswordView;
    private ProgressBar mProgressView;
    private Button mSignInButton;

    @InjectPresenter
    LoginActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.

        new Thread(() -> {
            Executor executor = new Executor();
            executor.setName("Igor");
            executor.setSurname("Akhmarov");
            executor.setRole(Role.DEVELOPER);
            executor.setPassword("123");
            Database.initDataBase(this).executorDao().addExecutor(executor);
        }).start();

        initViews();
    }

    @ProvidePresenter
    LoginActivityPresenter providePresenter() {
        return new LoginActivityPresenter(this);
    }

    private void initViews() {
        mLoginView = findViewById(R.id.login);
        mPasswordView = findViewById(R.id.password);
        mProgressView = findViewById(R.id.login_progress);
        mSignInButton = findViewById(R.id.email_sign_in_button);

        mSignInButton.setOnClickListener(v -> mPresenter.onSignButtonPressed(mLoginView.getText().toString(), mPasswordView.getText().toString()));
    }

    @Override
    public void showProgressBar() {
        mProgressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressView.setVisibility(View.GONE);
    }

    @Override
    public void setLogin(@NonNull String login) {
        mLoginView.setText(login);
    }

    @Override
    public void showError() {
        mPasswordView.setText("");
        Toast.makeText(this, R.string.user_not_found, Toast.LENGTH_LONG).show();
    }

    @Override
    public void openTasks() {
        startActivity(MainActivity.getIntent(this));
//        startActivity(ChoosingProjectActivity.getIntent(this));
    }

    @Override
    public void openProjectsList() {
        startActivity(ChoosingProjectActivity.getIntent(this));
    }

    @Override
    public void setUserId() {
        CurrentUser.setUserId(this, Long.parseLong(mLoginView.getText().toString()));
    }
}

