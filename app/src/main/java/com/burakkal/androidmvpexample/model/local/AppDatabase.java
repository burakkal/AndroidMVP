package com.burakkal.androidmvpexample.model.local;

import android.content.Context;

import com.burakkal.androidmvpexample.model.local.entity.User;
import com.burakkal.androidmvpexample.model.local.dao.UserDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "user.db")
                    .build();
        }
        return INSTANCE;
    }
}
