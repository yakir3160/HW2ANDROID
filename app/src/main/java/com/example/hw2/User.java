package com.example.hw2;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "users",
        indices = {@Index(value = {"userId"}, unique = true)})
public class User {
    @ColumnInfo(name = "imgUrl")
    public String imgUrl;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    public int userId;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "country")
    public String country;
    @ColumnInfo(name = "city")
    public String city;

}