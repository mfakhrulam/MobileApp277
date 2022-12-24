package com.example.mobileapp277.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE nim IN (:userNims)")
    List<User> loadAllByIds(int[] userNims);

    @Query("SELECT * FROM user WHERE nim = :nim AND password = :password LIMIT 1")
    User findByNimAndPassword(String nim, String password);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);
}