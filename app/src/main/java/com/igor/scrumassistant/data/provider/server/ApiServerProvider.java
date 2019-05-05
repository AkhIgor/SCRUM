package com.igor.scrumassistant.data.provider.server;

import android.content.Context;
import android.support.annotation.NonNull;

import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.data.network.NetworkService;
import com.igor.scrumassistant.data.provider.DataObserver;
import com.igor.scrumassistant.model.entity.CurrentUser;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServerProvider implements IApiServerProvider {

    private final DataObserver mObserver;
    private final NetworkService mNetworkService;
    private Context mContext;

    public ApiServerProvider(@NonNull DataObserver dataObserver, @NonNull Context context) {
        mNetworkService = NetworkService.getInstance();
        mObserver = dataObserver;
        mContext = context;
    }

    @Override
    public void getTeamOfTheProject() {

    }

    @Override
    public void getAllExecutors() {
    }

    @Override
    public void getExecutorById(long id) {
        mNetworkService.getJSONApi().getExecutorById(id)
                .enqueue(new Callback<Executor>() {

                    @Override
                    public void onResponse(@NonNull Call<Executor> call, @NonNull Response<Executor> response) {
                        Executor task = response.body();
                        if (task != null) {
                            mObserver.onEntitySuccess(task);
                        } else {
                            mObserver.failOnTaskById(new NullPointerException(), id);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Executor> call, @NonNull Throwable t) {
                        mObserver.failOnTaskById(t, id);
                    }
                });
    }

    @Override
    public void getExecutorsInCurrProject() {
        mNetworkService.getJSONApi().getExecutorInCurrProject(CurrentUser.getProjectId(mContext))
                .enqueue(new Callback<List<Executor>>() {
                    @Override
                    public void onResponse(Call<List<Executor>> call, Response<List<Executor>> response) {
                        List<Executor> executors = response.body();
                        if (executors != null) {
                            mObserver.onListSuccess(executors);
                        } else {
                            mObserver.failOnExecutorsInTheProject(new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Executor>> call, Throwable t) {
                        mObserver.failOnExecutorsInTheProject(t);
                    }
                });
    }

    @Override
    public void getAllTasks() {
    }

    @Override
    public void getTaskById(long id) {
        mNetworkService.getJSONApi().getTaskWithID(id)
                .enqueue(new Callback<Task>() {

                    @Override
                    public void onResponse(@NonNull Call<Task> call, @NonNull Response<Task> response) {
                        Task task = response.body();
                        if (task != null) {
                            mObserver.onEntitySuccess(task);
                        } else {
                            mObserver.failOnTaskById(new NullPointerException(), id);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Task> call, @NonNull Throwable t) {
                        mObserver.failOnTaskById(t, id);
                    }
                });
    }

    @Override
    public void getOpenTasks() {
        mNetworkService.getJSONApi().getTasks(State.OPEN)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                        List<Task> tasks = response.body();
                        if (tasks != null) {
                            mObserver.onListSuccess(tasks);
                        } else {
                            mObserver.failOnOpenTasks(new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                        mObserver.failOnOpenTasks(t);
                    }
                });
    }

    @Override
    public void getInWorkTasks() {
        mNetworkService.getJSONApi().getTasks(State.IN_WORK)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                        List<Task> tasks = response.body();
                        if (tasks != null) {
                            mObserver.onListSuccess(tasks);
                        } else {
                            mObserver.failOnInWorkTasks(new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                        mObserver.failOnInWorkTasks(t);
                    }
                });
    }

    @Override
    public void getDoneTasks() {
        mNetworkService.getJSONApi().getTasks(State.DONE)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                        List<Task> tasks = response.body();
                        if (tasks != null) {
                            mObserver.onListSuccess(tasks);
                        } else {
                            mObserver.failOnDoneTasks(new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                        mObserver.failOnDoneTasks(t);
                    }
                });
    }

    @Override
    public void getDeclinedTasks() {
        mNetworkService.getJSONApi().getTasks(State.DECLINED)
                .enqueue(new Callback<List<Task>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<Task>> call, @NonNull Response<List<Task>> response) {
                        List<Task> tasks = response.body();
                        if (tasks != null) {
                            mObserver.onListSuccess(tasks);
                        } else {
                            mObserver.failOnDeclinedTasks(new NullPointerException());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Task>> call, @NonNull Throwable t) {
                        mObserver.failOnDeclinedTasks(t);
                    }
                });
    }

    @Override
    public void getAllProjects() {
    }

    @Override
    public void addTask(@NonNull Task task) {

    }
}
