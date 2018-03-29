package com.example.softwareeng18.food4thought;

import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        Button requestATableButton = (Button) findViewById(R.id.requestATableButton);
        requestATableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to requestATable screen
                Intent requestATableIntent=new Intent(CustomerActivity.this,RequestTableActivity.class);
                startActivity(requestATableIntent);
            }
        });

        Button placeYourOrderButton = (Button) findViewById(R.id.placeYourOrderButton);
        placeYourOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to placeYourOrder screen
                Intent placeYourOrderIntent=new Intent(CustomerActivity.this,OrderActivity.class);
                startActivity(placeYourOrderIntent);
            }
        });


    }
}
