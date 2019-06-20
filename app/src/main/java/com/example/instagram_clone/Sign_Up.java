package com.example.instagram_clone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

public  class Sign_Up extends AppCompatActivity implements View.OnClickListener {

    private EditText mail, user, pass, fulname;
    private Button btm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        mail = findViewById(R.id.editText);
        user = findViewById(R.id.editText3);
        btm = findViewById(R.id.button);
        pass = findViewById(R.id.editText4);
        pass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) { //This is line of code is for the "Enter Button", When the Enter button tapped it starts to sign up
                    onClick(btm);
                }
                return false;
            }
        });
        fulname = findViewById(R.id.editText2);
        btm.setOnClickListener(Sign_Up.this);

 // This is to clear all the token section that are created.
        if (ParseUser.getCurrentUser() != null){
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View v) {

        if (mail.getText().toString().equals("") || fulname.getText().toString().equals("") || user.getText().toString().equals("") || pass.getText().toString().equals("")) {
            Toast.makeText(Sign_Up.this, "Please fill all the fields", Toast.LENGTH_LONG).show();
        } else {
            final ParseUser test = new ParseUser();
            test.setEmail(mail.getText().toString());
            test.put("Name", fulname.getText().toString());
            test.setUsername(user.getText().toString());
            test.setPassword(pass.getText().toString());



            final ProgressDialog progressDialog = new ProgressDialog(this);
           progressDialog.setMessage("Wait for a moment");
          progressDialog.show();
                test.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(Sign_Up.this, "Your sign was successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Sign_Up.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                    progressDialog.dismiss();

                }

            });

        }
        Intent intent = new Intent(Sign_Up.this, Instagram.class);
        startActivity(intent);
    }

    public void enter(View view) {
        try {
            InputMethodManager keyboardenter = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            keyboardenter.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
