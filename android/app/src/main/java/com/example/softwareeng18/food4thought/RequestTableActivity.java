package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_table);

        Button submitButton = (Button) findViewById(R.id.submitButton);     //submit button for number of customers in party
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText guests = (EditText) findViewById(R.id.numGuests);
                String num = guests.getText().toString();
                int guestsNum = Integer.parseInt(num);
                /*Intent numGuests=new Intent(RequestTableActivity.this,RequestTableCustomersActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numGuests", guestsNum);
                numGuests.putExtras(bundle);
                startActivity(numGuests);*/
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            }
        });
        Button addCustButton = (Button) findViewById(R.id.addGuestButton);
        /*EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();*/
        addCustButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {  //adding customer to the db
                EditText firstName = (EditText) findViewById(R.id.firstName);
                EditText lastName = (EditText) findViewById(R.id.lastName);
                String first = firstName.getText().toString();
                String last = lastName.getText().toString();
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://192.168.0.107:8080";
                url = url+"/customers?firstName="+ first + "&lastName=" + last;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().length()==0){
                                    Toast toast = Toast.makeText(getApplicationContext(), "Can't add customer", Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                else{
                                    try {
                                        JSONObject resp = new JSONObject(response);
                                        Toast toast;

                                        /*switch(resp.getString("role")){
                                            case "Manager":
//                                                resp = new JSONObject(response);
                                                Intent managerIntent=new Intent(LoginActivity.this,ManagerActivity.class);
                                                startActivity(managerIntent);
                                                break;
                                            case "Chef":
                                                resp = new JSONObject(response);
                                                Intent chefIntent=new Intent(LoginActivity.this,ChefActivity.class);
                                                startActivity(chefIntent);
                                                toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
                                                toast.show();
                                                break;
                                            case "Waiter":
                                                resp = new JSONObject(response);
                                                toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
                                                toast.show();
                                                Intent waiterIntent=new Intent(LoginActivity.this,WaiterActivity.class);
                                                startActivity(waiterIntent);
                                                break;
                                            case "Busboy":
                                                resp = new JSONObject(response);
                                                Intent busboyIntent=new Intent(LoginActivity.this,BusBoyActivity.class);
                                                startActivity(busboyIntent);
                                                toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
                                                toast.show();;
                                                break;
                                        }*/


//                                        resp = new JSONObject(response);
                                        toast = Toast.makeText(getApplicationContext(), "Cust Added", Toast.LENGTH_SHORT);
                                        toast.show();
                                    }
                                    catch(Exception e){
                                        Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT);

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





                // Toast toast = Toast.makeText(getApplicationContext(), "Customer Added, you may add more customers or click view available tables", Toast.LENGTH_LONG);
                //toast.show();
                //add customer with first and last name to db
                //make toast saying customer added
               /* EditText guests = (EditText) findViewById(R.id.numGuests);
                String num = guests.getText().toString();
                int guestsNum = Integer.parseInt(num);
                /*Intent numGuests=new Intent(RequestTableActivity.this,RequestTableCustomersActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numGuests", guestsNum);
                numGuests.putExtras(bundle);
                startActivity(numGuests);*/
                // RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            }
        });
    }
}