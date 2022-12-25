package com.example.mobileapp277.database;

import android.content.Context;

import androidx.room.Room;

public class AppDBProvider {
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if(AppDBProvider.instance == null)
        {
            AppDBProvider.instance = Room.databaseBuilder(
                    context, AppDatabase.class, "mobileapp277.db"
            ).allowMainThreadQueries().build();
        }
        return AppDBProvider.instance;
    }
}
