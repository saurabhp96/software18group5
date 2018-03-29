package com.example.softwareeng18.food4thought;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button employeeLoginButton = (Button) findViewById(R.id.employeeLoginButton);
        employeeLoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to login screen
                Intent employeeLoginIntent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(employeeLoginIntent);
            }
        });

        Button customerButton = (Button) findViewById(R.id.customerButton);
        customerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to customer screen
                Intent customerIntent=new Intent(MainActivity.this,CustomerActivity.class);
                startActivity(customerIntent);

            }
        });




    }
}
//test

