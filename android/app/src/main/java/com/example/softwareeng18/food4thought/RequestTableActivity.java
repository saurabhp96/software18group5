package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class RequestTableActivity extends AppCompatActivity {
    int pid = 0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_table);
        String firstN = "";
        String lastN = "";

        Button viewTablesButton = (Button) findViewById(R.id.viewTablesButton);     //submit button for number of customers in party

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
                String url = "http://172.30.20.134:8080";
                url = url+"/customers?firstName="+ first + "&lastName=" + last;
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {



                                    JSONObject resp = new JSONObject(response);

                                    pid = resp.getInt("custID");
                                    // Toast toast;

                                    //JSONArray jsonArray = new JSONArray(response);

                                    //Toast toast = Toast.makeText(getApplicationContext(), "Hello:"+ pid, Toast.LENGTH_SHORT);
                                    //toast.show();
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                    Toast mtoast =  Toast.makeText(getApplicationContext(), "Exception"+e, Toast.LENGTH_SHORT);
                                    mtoast.show();
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

        viewTablesButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText guests = (EditText) findViewById(R.id.numGuests);
                String num = guests.getText().toString();
                int guestsNum = Integer.parseInt(num);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                // String url = "http://192.168.1.222:8080"; //mine
                String url = "http://172.30.20.134:8080";
                url = url+"/findtables?numPeople="+ guestsNum;
                // Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG)
                //Toast toast = Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT);
                //toast.show();
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if (response.trim().length() == 0) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "incorrect credentials", Toast.LENGTH_SHORT);
                                    toast.show();
                                } else {
                                    try {
                                       /* Iterator iter = new Iterator() {
                                            @Override
                                            public boolean hasNext() {
                                                return false;
                                            }

                                            @Override
                                            public Object next() {
                                                return null;
                                            }
                                        };
                                        JSONObject resp = new JSONObject(response);
                                        Toast toast;
                                        while (iter.hasNext()) {
                                            Object key = iter.next();
                                            try {
                                                Object value = resp.get((String)key);
                                            } catch (Exception e) {
                                                // Something went wrong!
                                            }
                                        }*/
                                        // JSONObject resp = new JSONObject(response);
                                        Toast toast;
                                        JSONArray res = new JSONArray(response);
                                        ArrayList<Integer> ids= new ArrayList<Integer>();
                                        for (int i = 0; i< res.length(); i++)
                                        {
                                            JSONObject table = res.getJSONObject(i);
                                            int id = table.getInt("tableId");
                                            ids.add(id);
                                        }
                                        //JSONArray arr = res.getJSONArray("tableId");
                                        //toast = Toast.makeText(getApplicationContext(), "Hello: "+ids.get(0), Toast.LENGTH_LONG);
                                        //toast.show();
                                        Intent tables=new Intent(RequestTableActivity.this,RequestTableCustomersActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putIntegerArrayList("ids", ids);
                                        bundle.putInt("custID", pid);
                                        tables.putExtras(bundle);
                                        startActivity(tables);
                                       /* switch(resp.getString("role")){
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
                                        }


//                                        resp = new JSONObject(response);*/
                                        // toast = Toast.makeText(getApplicationContext(), resp.getString("role"), Toast.LENGTH_SHORT);
                                        //toast.show();
                                    } catch (Exception e) {
                                        Toast mytoast = Toast.makeText(getApplicationContext(), "Exception: " + e, Toast.LENGTH_SHORT);
                                        mytoast.show();

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




                /*Intent numGuests=new Intent(RequestTableActivity.this,RequestTableCustomersActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("numGuests", guestsNum);
                numGuests.putExtras(bundle);
                startActivity(numGuests);*/
                //RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            }
        });
    }
}
