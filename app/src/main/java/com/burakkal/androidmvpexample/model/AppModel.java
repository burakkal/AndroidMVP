package com.burakkal.androidmvpexample.model;

import com.burakkal.androidmvpexample.model.local.AppDatabase;
import com.burakkal.androidmvpexample.model.local.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class AppModel implements AppDataSource {

    private AppDatabase appDatabase;

    public AppModel(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }

    @Override
    public Flowable<List<User>> getUsers() {
        return appDatabase.userDao().getAllUsers();
    }

    @Override
    public Completable addUser(User user) {
        return Completable.fromAction(() -> appDatabase.userDao().insert(user));
    }
}
