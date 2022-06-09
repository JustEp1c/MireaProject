package com.mirea.sumachev.mireaproject.room;

import android.app.Application;

import androidx.room.Room;

public class DatabaseCreator extends Application {

    private static DatabaseCreator instance;

    private StudentDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, StudentDatabase.class, "StudentDatabase").allowMainThreadQueries().build();
    }

    public static DatabaseCreator getInstance() {
        return instance;
    }

    public StudentDatabase getDatabase() {
        return database;
    }
}
