package com.igor.scrumassistant.data.provider;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.igor.scrumassistant.data.Customer;
import com.igor.scrumassistant.data.provider.database.ApiDatabaseProvider;
import com.igor.scrumassistant.data.provider.server.ApiServerProvider;
import com.igor.scrumassistant.model.entity.AppEntity;
import com.igor.scrumassistant.model.entity.Executor;
import com.igor.scrumassistant.model.entity.Project;
import com.igor.scrumassistant.model.entity.Task;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

//TODO: разобраться с observeOn
public class CommonDataProvider implements IApiDataProvider, DataObserver {

    private static CommonDataProvider ourInstance;

    private final ApiServerProvider mServerProvider;
    private final ApiDatabaseProvider mDatabaseProvider;

    private List mEntityList;
    private AppEntity mEntity;

    public static CommonDataProvider getInstance(@NonNull Context context) {
        if (ourInstance == null) {
            ourInstance = new CommonDataProvider(context);
        }
        return ourInstance;
    }

    private CommonDataProvider(@NonNull Context context) {
        mServerProvider = new ApiServerProvider(this, context);
        mDatabaseProvider = new ApiDatabaseProvider(context);
    }

    @Override
    public void getAllExecutors() {

    }

    @Override
    public void addProject(@NonNull Project project) {
        mDatabaseProvider.addProject(project);
    }

    @Override
    public void getExecutorsInTheProject(@NonNull Customer customer) {
        Observable.just(mEntityList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> setUpList(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getExecutorsInCurrProject();
    }

    //TODO: Прочитать про Retrofit
    public void getAllTasks() {
        // Проверка на наличие Интернета и если все ок, то
        // идем в ApiServerProvider и получаем данные оттуда, а после возвращаем и записываем их в БД, иначе -
        // - читаем данные с БД черрез AsyncTask

        // - нужно ли?
    }

    @Override
    public void getTaskById(@NonNull Customer customer, long id) {
        Observable.just(mEntity)
                .subscribe(d -> setUpEntity(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getTaskById(id);
    }

    @Override
    public void getOpenTasks(@NonNull Customer customer) {
        Observable.just(mEntityList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> setUpList(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getOpenTasks();
    }

    @Override
    public void getInWorkTasks(@NonNull Customer customer) {
        Observable.just(mEntityList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> setUpList(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getInWorkTasks();
    }

    @Override
    public void getDoneTasks(@NonNull Customer customer) {
        Observable.just(mEntityList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> setUpList(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getDoneTasks();
    }

    @Override
    public void getDeclinedTasks(@NonNull Customer customer) {
        Observable.just(mEntityList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> setUpList(d, customer), e -> Log.e("Error", "Received Error:" + e))
                .dispose();

        mServerProvider.getDeclinedTasks();
    }

    @Override
    public void getAllProjects() {

    }

    @Override
    public void addTask(@NonNull Task task) {

    }

    private void initList(@NonNull List list) {
        mEntityList = list;
    }

    private void setUpList(@NonNull List list, @NonNull Customer customer) {
        customer.setList(list);
        mEntityList = null;
    }

    private void setUpEntity(@NonNull AppEntity entity, @NonNull Customer customer) {
        customer.setEntity(entity);
        mEntityList = null;
    }

    private void initEntity(@NonNull AppEntity entity) {
        mEntity = entity;
    }

    // Уведомления

    @Override
    public void onListSuccess(@NonNull List entities) {
        mEntityList = entities;
    }

    @Override
    public void onEntitySuccess(@NonNull AppEntity entity) {
        mEntity = entity;
    }

    @Override
    public void failOnExecutorsInTheProject(@Nullable Throwable exception) {
        Observable.just(mDatabaseProvider.getExecutorInCurrProject())
                .subscribe(this::initList, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }

    @Override
    public void failOnTaskById(@Nullable Throwable exception, long id) {
        Observable.just(mDatabaseProvider.getTaskById(id))
                .subscribe(this::initEntity, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }

    @Override
    public void failOnOpenTasks(@Nullable Throwable exception) {
        Observable.just(mDatabaseProvider.getOpenTasks())
                .subscribe(this::initList, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }

    @Override
    public void failOnInWorkTasks(@Nullable Throwable exception) {
        Observable.just(mDatabaseProvider.getInWorkTasks())
                .subscribe(this::initList, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }

    @Override
    public void failOnDoneTasks(@Nullable Throwable exception) {
        Observable.just(mDatabaseProvider.getDoneTasks())
                .subscribe(this::initList, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }

    @Override
    public void failOnDeclinedTasks(@Nullable Throwable exception) {
        Observable.just(mDatabaseProvider.getDeclinedTasks())
                .subscribe(this::initList, e -> Log.e("Error", "Received Error:" + e))
                .dispose();
    }
}
