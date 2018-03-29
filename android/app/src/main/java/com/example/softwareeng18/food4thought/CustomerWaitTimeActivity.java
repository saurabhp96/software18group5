//Katheryne's code


package com.example.softwareeng18.food4thought;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class CustomerWaitTimeActivity extends AppCompatActivity {

    private Button button;
    private TextView resultText;

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_wait_time);

        //request waiter
        button = (Button) findViewById(R.id.requestWaiter);
        resultText = (TextView) findViewById(R.id.result);
        button.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {
                showInputDialog();
            }
        });


    }

    protected void showInputDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(CustomerWaitTimeActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_call_waiter, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CustomerWaitTimeActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}


