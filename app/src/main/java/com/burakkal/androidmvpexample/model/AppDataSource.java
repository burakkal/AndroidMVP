package com.burakkal.androidmvpexample.model;

import com.burakkal.androidmvpexample.model.local.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public interface AppDataSource {

    Flowable<List<User>> getUsers();

    Completable addUser(User user);
}
