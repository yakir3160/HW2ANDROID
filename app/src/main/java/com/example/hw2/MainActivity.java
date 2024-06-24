package com.example.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hw2.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView firstNameTextView, lastNameTextView, emailTextView, cityTextView, countryTextView, ageTextView;
    private ImageView userImageView;
    private UsersDatabase database;
    private Result currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = UsersDatabase.getInstance(this);

        firstNameTextView = findViewById(R.id.firstNameTextView);
        lastNameTextView = findViewById(R.id.lastNameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        cityTextView = findViewById(R.id.cityTextView);
        countryTextView = findViewById(R.id.countryTextView);
        ageTextView = findViewById(R.id.ageTextView);
        userImageView = findViewById(R.id.userImageView);

        binding.viewCollectionButton.setOnClickListener(v -> openUsersActivity());
        binding.nextUserButton.setOnClickListener(v -> getUserFromApi());
        binding.addUserButton.setOnClickListener(v -> addUserToDatabase());

        getUserFromApi();
    }

    private void openUsersActivity() {
        Intent intent = new Intent(this, UsersActivity.class);
        startActivity(intent);
    }

    private void getUserFromApi() {
        Retrofit retrofit = UserAPIClient.getClient();
        UserService service = retrofit.create(UserService.class);
        Call<Users> callAsync = service.getUsers(null, null, null);
        callAsync.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Result> results = response.body().getResults();
                    if (!results.isEmpty()) {
                        currentUser = results.get(0);
                        displayUser(currentUser);
                    } else {
                        onFailure(call, new Throwable("No users found"));
                    }
                } else {
                    onFailure(call, new Throwable("Unsuccessful response"));
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                firstNameTextView.setText("Error");
                lastNameTextView.setText("Error");
                emailTextView.setText("Email: Error");
                cityTextView.setText("City: Error");
                countryTextView.setText("Country: Error");
                ageTextView.setText("Age: Error");
                Glide.with(MainActivity.this)
                        .load(R.drawable.error)
                        .into(userImageView);
                currentUser = null;
            }
        });
    }

    private void displayUser(Result user) {
        firstNameTextView.setText("First Name: " + user.getName().getFirst());
        lastNameTextView.setText("Last Name: " + user.getName().getLast());
        emailTextView.setText("Email: " + user.getEmail());
        cityTextView.setText("City: " + user.getLocation().getCity());
        countryTextView.setText("Country: " + user.getLocation().getCountry());
        ageTextView.setText("Age: " + user.getDob().getAge());

        Glide.with(this)
                .load(user.getPicture().getLarge())
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .into(userImageView);
    }

    private void addUserToDatabase() {
        if (currentUser != null) {
            String firstName = currentUser.getName().first;
            String lastName = currentUser.getName().last;
            String city = currentUser.getLocation().city;
            String country = currentUser.getLocation().country;

            User existingUser = database.userDao().getUserByFields(firstName, lastName, country, city);

            if (existingUser == null) {
                User user1 = new User();
                user1.imgUrl = currentUser.getPicture().large;
                user1.firstName = currentUser.getName().first;
                user1.lastName = currentUser.getName().last;
                user1.city = currentUser.getLocation().city;
                user1.country = currentUser.getLocation().country;

                database.userDao().insertUser(user1);
                Toast.makeText(this, "User added to collection", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "User already exists in the collection", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
