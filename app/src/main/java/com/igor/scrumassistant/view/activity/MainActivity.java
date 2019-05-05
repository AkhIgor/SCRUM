package com.igor.scrumassistant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.igor.scrumassistant.R;
import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.presentation.activity.MainActivityPresenter;
import com.igor.scrumassistant.view.MainActivityView;
import com.igor.scrumassistant.view.TaskStateChangedObserver;
import com.igor.scrumassistant.view.activity.arello.MvpAppCompatActivity;
import com.igor.scrumassistant.view.fragment.AddingDialogFragment;
import com.igor.scrumassistant.view.fragment.DoneSceneFragment;
import com.igor.scrumassistant.view.fragment.InWorkSceneFragment;
import com.igor.scrumassistant.view.fragment.ToDoSceneFragment;

public class MainActivity extends MvpAppCompatActivity
        implements MainActivityView, TaskStateChangedObserver {

    private static final int CREATE_TASK = 0;
    private static final int CREATE_PROJECT = 1;

    private static final int SEGMENTS = 3;

    private static final String TO_DO_BAR = "To do bar";
    private static final String IN_WORK_BAR = "In work bar";
    private static final String DONE_BAR = "Done bar";

    private ToDoSceneFragment mToDoFragment = ToDoSceneFragment.newInstance();
    private InWorkSceneFragment mInWorkFragment = InWorkSceneFragment.newInstance();
    private DoneSceneFragment mDoneFragment = DoneSceneFragment.newInstance();

    private AddingDialogFragment mAddingDialog = AddingDialogFragment.newInstance();
    private FloatingActionButton mFab;

    public static Intent getIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @InjectPresenter
    MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        new Thread(() -> {
//            Task openTask = new Task();
//            openTask.setState(State.OPEN);
//            openTask.setPriority(Priority.CRITICAL);
//            openTask.setPurpose("Доделать диплом");
//            openTask.setProjectId(CurrentUser.getProjectId(this));
//            openTask.setExecutorName("Игорь Ахмаров");
//            openTask.setCreatorName("Игорь Ахмаров");
//
//            Task inWorkTask = new Task();
//            inWorkTask.setState(State.IN_WORK);
//            inWorkTask.setPriority(Priority.MEDIUM);
//            inWorkTask.setProjectId(CurrentUser.getProjectId(this));
//            inWorkTask.setPurpose("Доделать диплом");
//            inWorkTask.setExecutorName("Игорь Ахмаров");
//            inWorkTask.setCreatorName("Игорь Ахмаров");
//
//            Task doneTask = new Task();
//            doneTask.setState(State.DONE);
//            doneTask.setPriority(Priority.HIGH);
//            doneTask.setProjectId(CurrentUser.getProjectId(this));
//            doneTask.setPurpose("Доделать диплом");
//            doneTask.setExecutorName("Игорь Ахмаров");
//            doneTask.setCreatorName("Игорь Ахмаров");
//
//            Database db = Database.initDataBase(this);
//            db.taskDao()
//                    .addTask(openTask);
//
//            db.taskDao()
//                    .addTask(inWorkTask);
//
//            db.taskDao()
//                    .addTask(doneTask);
//        }).start();
        initViews();
    }

    @ProvidePresenter
    MainActivityPresenter providePresenter() {
        return new MainActivityPresenter();
    }

    public void openTaskCreatingActivity() {
        startActivityForResult(TaskCreatingActivity.newIntent(this), CREATE_TASK);
    }

    public void openProjectCreatingActivity() {
        startActivityForResult(ProjectCreatingActivity.newIntent(this), CREATE_PROJECT);
    }

    @Override
    public void notifyTaskStateChanged(@NonNull State state, @NonNull Task task) {
        switch (state) {
            case OPEN: {
                mToDoFragment.addChangedTaskStateToList(task);
                break;
            }
            case IN_WORK: {
                mInWorkFragment.addChangedTaskStateToList(task);
                break;
            }
            case DONE: {
                mDoneFragment.addChangedTaskStateToList(task);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showAddingDialogFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.small_menu_fragment_container, mAddingDialog)
                .commit();
    }

    @Override
    public void hideAddingDialogFragment() {
        getSupportFragmentManager().beginTransaction()
                .remove(mAddingDialog)
//                .hide(mAddingDialog)
                .commit();
    }

    @Override
    public void showAddingProjectDialogFragment() {

    }

    @Override
    public void hideAddingProjectDialogFragment() {

    }

    @Override
    public void addTaskToList(@NonNull Task task, @NonNull State state) {
        switch (state) {
            case OPEN: {
                mToDoFragment.addTaskToList(task);
                break;
            }
            case IN_WORK: {
                mInWorkFragment.addTaskToList(task);
                break;
            }
            default: {
                mDoneFragment.addTaskToList(task);
                break;
            }
        }
    }

    @Override
    public void checkOutToProject(long projectId) {
        CurrentUser.setProjectId(this, projectId);
        mToDoFragment.checkOut();
        mInWorkFragment.checkOut();
        mDoneFragment.checkOut();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.onResult(requestCode, resultCode, data);
    }


    private void initViews() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(v -> mPresenter.onFabClicked());
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mToDoFragment;
                case 1:
                    return mInWorkFragment;
                case 2:
                    return mDoneFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return SEGMENTS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return TO_DO_BAR;
                case 1:
                    return IN_WORK_BAR;
                case 2:
                    return DONE_BAR;
            }
            return null;
        }
    }
}
