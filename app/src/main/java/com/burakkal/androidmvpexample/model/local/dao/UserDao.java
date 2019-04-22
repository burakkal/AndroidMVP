package com.burakkal.androidmvpexample.model.local.dao;

import com.burakkal.androidmvpexample.model.local.entity.User;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    Flowable<List<User>> getAllUsers();

    @Insert
    void insert(User user);
}
