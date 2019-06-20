package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseUser;

public class Instagram extends AppCompatActivity implements View.OnClickListener {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);
        setTitle("Login");
        button = findViewById(R.id.button2);
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
        button.setOnClickListener(Instagram.this);
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(Instagram.this, "Check your spam box and Try again later", Toast.LENGTH_LONG).show();

    }
}
