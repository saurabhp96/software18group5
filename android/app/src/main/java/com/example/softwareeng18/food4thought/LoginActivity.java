package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    public static String BASE_ADDRESS = "http://172.30.20.134:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView loginErrorWarning = (TextView) findViewById(R.id.loginErrorWarning);
        loginErrorWarning.setText("");
//        RequestQueue queue = Volley.newRequestQueue(this);


        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText usernameEditText = (EditText) findViewById(R.id.username);
                EditText passwordEditText = (EditText) findViewById(R.id.password);
                final String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = BASE_ADDRESS;
                url = url+"/login?empid="+ enteredUsername + "&password=" + enteredPassword;

// Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().length()==0){
                                    Toast toast = Toast.makeText(getApplicationContext(), "incorrect credentials", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                else{
                                    try {
                                        JSONObject resp = new JSONObject(response);
                                        Toast toast;

                                        Intent shiftIntent = new Intent(LoginActivity.this, ShiftActivity.class);
                                        shiftIntent.putExtra("role", resp.getString("role"));
                                        shiftIntent.putExtra("empid", resp.getString("empID"));
                                        startActivity(shiftIntent);

//                                        switch(resp.getString("role")){
//                                            case "Manager":
////                                                resp = new JSONObject(response);
//                                                Intent managerIntent=new Intent(LoginActivity.this,ManagerActivity.class);
//                                                startActivity(managerIntent);
//                                                break;
//                                            case "Chef":
//                                                resp = new JSONObject(response);
//                                                Intent chefIntent=new Intent(LoginActivity.this,ChefActivity.class);
//                                                startActivity(chefIntent);
//                                                toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
//                                                toast.show();
//                                                break;
//                                            case "Waiter":
//                                                resp = new JSONObject(response);
//                                                toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
//                                                toast.show();
//                                                Intent waiterIntent=new Intent(LoginActivity.this,WaiterActivity.class);
//                                                startActivity(waiterIntent);
//                                                break;
//
//                                        }


                                        toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                    catch(Exception e){
                                        e.printStackTrace();
                                        Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT).show();

                                    }
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Context context = getApplicationContext();
                        CharSequence text = "Server Error!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

                queue.add(stringRequest);


            }
        });


        //Keep this code here
        //    until we put the backend on AWS,
        //    because we won't be able to log in
        //    (or get to the manager/chef/waiter screen)
        //    to test the app while off-campus
        //    until the backend gets put on AWS

      Button manager = (Button) findViewById(R.id.manager);
        manager.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent managerIntent=new Intent(LoginActivity.this,ManagerActivity.class);
                startActivity(managerIntent);
               }
        });

        Button waiter = (Button) findViewById(R.id.waiter);
        waiter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent waiterIntent=new Intent(LoginActivity.this,WaiterActivity.class);
                startActivity(waiterIntent);
            }
        });

        Button chef = (Button) findViewById(R.id.chef);
        chef.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent chefIntent=new Intent(LoginActivity.this,ChefActivity.class);
                startActivity(chefIntent);
            }
        });

        Button shifts = (Button) findViewById(R.id.shifts);
        shifts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent shiftIntent = new Intent(LoginActivity.this, ShiftActivity.class);
                shiftIntent.putExtra("role", "Chef");
                shiftIntent.putExtra("empid", "2");
                startActivity(shiftIntent);
//                Toast.makeText(getApplicationContext(),"bye",Toast.LENGTH_SHORT);
            }
        });


    }

}
