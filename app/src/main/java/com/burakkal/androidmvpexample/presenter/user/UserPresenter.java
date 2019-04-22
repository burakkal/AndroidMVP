package com.burakkal.androidmvpexample.presenter.user;

import com.burakkal.androidmvpexample.model.AppModel;
import com.burakkal.androidmvpexample.model.local.entity.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter implements UserContract.Presenter {

    private AppModel model;
    private UserContract.View view;
    private CompositeDisposable compositeDisposable;

    public UserPresenter(AppModel model, UserContract.View view) {
        this.model = model;
        this.view = view;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void loadUsers() {
        Disposable disposable = model.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if (users.isEmpty()) {
                        view.showNoUser();
                    } else {
                        view.showUsers(users);
                    }
                }, throwable -> view.showGetUserError());
        compositeDisposable.add(disposable);
    }

    @Override
    public void addUser(String name, String surname) {
        Disposable disposable = model.addUser(createUser(name, surname))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> view.showAddUserSuccess(), throwable -> view.showAddUserError());
        compositeDisposable.add(disposable);
    }

    private User createUser(String name, String surname) {
        return new User(name, surname);
    }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
        view = null;
    }
}
