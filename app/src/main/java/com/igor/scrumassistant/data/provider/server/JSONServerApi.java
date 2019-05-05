package com.igor.scrumassistant.data.provider.server;

import com.igor.scrumassistant.data.constants.State;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Task;
import com.igor.scrumassistant.model.entity.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JSONServerApi {

    @GET("/tasks/{mId}")
    Call<Task> getTaskWithID(@Path("mId") long id);

    @GET("/tasks/{mState}")
    Call<List<Task>> getTasks(@Path("mState") State state);

    @GET("/executors/{mId}")
    Call<Executor> getExecutorById(@Path("mId") long id);

    @GET("/executors/{mProjectId}")
    Call<List<Executor>> getExecutorInCurrProject(@Path("mProjectId") long projectId);

    @GET("/teams/")
    Call<List<Team>> getTeams();
}
