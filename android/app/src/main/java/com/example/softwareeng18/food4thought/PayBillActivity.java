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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayBillActivity extends AppCompatActivity {

    private Button button;
    private TextView resultText;
    private double bill;


    protected void onCreate(Bundle savedInstanceState) {
        bill=0;

        Bundle bundle=getIntent().getExtras();
        final int custID=bundle.getInt("custID");
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url=getString(R.string.url);
        String getRequest=url+"/order/"+custID;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, getRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray orderList=new JSONArray(response);
                    for(int i=0; i<orderList.length(); i++){
                        JSONObject orderItem=orderList.getJSONObject(i);
                        if(orderItem.getInt("custID")==custID){
                            String name=orderItem.getString("itemName");

                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_bill);


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

        LayoutInflater layoutInflater = LayoutInflater.from(PayBillActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_call_waiter, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(PayBillActivity.this);
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