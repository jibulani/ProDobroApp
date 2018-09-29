package com.eugene.prodobroapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.eugene.prodobroapp.data.source.local.AppDatabase;

/**
 * Created by eugene on 29.09.18.
 */

public class App extends Application {

    public static App instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

}
