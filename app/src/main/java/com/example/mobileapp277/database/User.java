package com.example.mobileapp277.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String nim;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "password")
    public String password;
}
