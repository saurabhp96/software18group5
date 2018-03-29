
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

public class OrderActivity extends AppCompatActivity {

    private Button button;
    private TextView resultText;
    private Button button2;
    private TextView resultText2;

    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        //customer views wait time for orders
        Button PlaceOrder = (Button) findViewById(R.id.PlaceOrder);
        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to customer screen
                Intent customerIntent=new Intent(OrderActivity.this,CustomerWaitTimeActivity.class);
                startActivity(customerIntent);

            }
        });

        //customer goes to pay bill
        Button PayBill = (Button) findViewById(R.id.PayBill);
        PayBill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to customer screen
                Intent customerIntent=new Intent(OrderActivity.this,PayBillActivity.class);
                startActivity(customerIntent);

            }
        });

        //priority order
        button2 = (Button) findViewById(R.id.Priority2);
        resultText2 = (TextView) findViewById(R.id.result2);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                showInputDialog2();
            }
        });

        //request waiter
        button = (Button) findViewById(R.id.RequestWaiter);
        resultText = (TextView) findViewById(R.id.result);
        button.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    showInputDialog();
                }
            });




        }




        //request waiter
    protected void showInputDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(OrderActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_call_waiter, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderActivity.this);
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




        //priority order
    protected void showInputDialog2() {

        LayoutInflater layoutInflater = LayoutInflater.from(OrderActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_priority, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext2);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Yes ($5 fee)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText2.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("No",
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

