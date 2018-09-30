package com.eugene.prodobroapp;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.eugene.prodobroapp.data.source.local.AppDatabase;
import com.eugene.prodobroapp.data.source.remote.MessageServer;

/**
 * Created by eugene on 29.09.18.
 */

public class App extends Application {

    public static App instance;
    private AppDatabase appDatabase;
    private MessageServer messageServer;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
        messageServer = new MessageServer();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public MessageServer getMessageServer() {
        return messageServer;
    }
}
