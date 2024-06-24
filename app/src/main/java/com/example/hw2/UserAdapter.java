package com.example.hw2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hw2.R;
import com.example.hw2.User;
import com.example.hw2.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_view, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(user.imgUrl)
                .placeholder(R.drawable.placeholder) // Optional placeholder
                .error(R.drawable.error) // Optional error placeholder
                .into(holder.getUserImageView());

        holder.getTextViewUserFirstName().setText(user.firstName);
        holder.getTextViewUserLastName().setText(user.lastName);
        holder.getTextViewUserCountry().setText(user.country);
        holder.getTextViewUserCity().setText(user.city);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
