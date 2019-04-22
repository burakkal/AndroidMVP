package com.burakkal.androidmvpexample.presenter.user;

import com.burakkal.androidmvpexample.model.local.entity.User;

import java.util.List;

public interface UserContract {

    interface View {

        void showUsers(List<User> users);

        void showNoUser();

        void showGetUserError();

        void showAddUserSuccess();

        void showAddUserError();
    }

    interface Presenter {

        void loadUsers();

        void addUser(String name, String surname);

        void unsubscribe();
    }
}
