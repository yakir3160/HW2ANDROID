package com.example.hw2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE first_name = :firstName AND last_name = :lastName AND country = :country AND city = :city LIMIT 1")
    User getUserByFields(String firstName, String lastName, String country, String city);

    @Insert()
    void insertUser(User user);
}