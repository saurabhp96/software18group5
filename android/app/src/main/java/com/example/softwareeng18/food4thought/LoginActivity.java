package com.example.softwareeng18.food4thought;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView loginErrorWarning = (TextView) findViewById(R.id.loginErrorWarning);
        loginErrorWarning.setText("");


        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText usernameEditText = (EditText) findViewById(R.id.username);
                EditText passwordEditText = (EditText) findViewById(R.id.password);
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();
                // check login credentials (enteredUsername and enteredPassword)

//                if (login not successful)
//                {
//                    loginErrorWarning.setText("Login Unsuccessful");
//                }
//                else
//                {
//                    //move to employee/manager screen
//                }

            }
        });
    }
}
