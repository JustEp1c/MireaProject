/*package com.mirea.sumachev.mireaproject.room;

import android.app.Application;
import android.arch.persistence.room.Room;

public class DatabaseCreator extends Application {

    private static DatabaseCreator instance;

    private StudentDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        database = Room.databaseBuilder(this, StudentDatabase.class, "StudentDatabase").build();
    }

    public static DatabaseCreator getInstance() {
        return instance;
    }

    public StudentDatabase getDatabase() {
        return database;
    }
}
*/