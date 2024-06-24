package com.example.hw2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hw2.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private ImageView userImageView;
    private TextView textViewUserFirstName;
    private TextView textViewUserLastName;
    private TextView textViewUserCountry;
    private TextView textViewUserCity;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userImageView = itemView.findViewById(R.id.userImageView);
        textViewUserFirstName = itemView.findViewById(R.id.textViewFname);
        textViewUserLastName = itemView.findViewById(R.id.textViewLname);
        textViewUserCountry = itemView.findViewById(R.id.textViewCountry);
        textViewUserCity = itemView.findViewById(R.id.textViewCity);
    }

    public ImageView getUserImageView() {return userImageView;}

    public TextView getTextViewUserFirstName() {return textViewUserFirstName;}

    public TextView getTextViewUserLastName() {
        return textViewUserLastName;
    }

    public TextView getTextViewUserCountry() {
        return textViewUserCountry;
    }

    public TextView getTextViewUserCity() {
        return textViewUserCity;
    }
}
