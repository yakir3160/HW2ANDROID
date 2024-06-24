package com.example.hw2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hw2.User;
import com.example.hw2.UserAdapter;
import com.example.hw2.UsersDatabase;
import com.example.hw2.databinding.UsersActivityBinding;

import java.util.List;

public class UsersActivity extends AppCompatActivity {

    private UsersActivityBinding binding;
    private UsersDatabase database;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = UsersActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = UsersDatabase.getInstance(this);

        List<User> userList = database.userDao().getAll();

        adapter = new UserAdapter(userList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }
}
