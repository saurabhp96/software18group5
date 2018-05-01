package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RequestTableCustomersActivity extends AppCompatActivity {
    int custID = 0;
    int tableID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_table_customers);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Intent intentExtras = getIntent();
        final Bundle extras = intentExtras.getExtras();
        ArrayList<Integer> arr = extras.getIntegerArrayList("ids");
        //custID = extras.getInt("custID");
        //Toast toast = Toast.makeText(getApplicationContext(), "Hello: " +arr.get(0), Toast.LENGTH_LONG);
        ArrayAdapter<Integer> itemsAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_list_item_1, arr);
        ListView listView = (ListView) findViewById(R.id.listTables);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Mainthis, numbers.get(i), Toast.LENGTH_SHORT).show();
                tableID = Integer.parseInt(((TextView) view).getText().toString());
                custID = extras.getInt("custID");
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                //String url = "http://192.168.1.222:8080";
                String url = "http://172.30.20.134:8080";
                url = url + "/seatcustomer?tableID=" + tableID + "&custID=" + custID;          //adding customer to allTables
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                               /* if(response.trim().length()==0){
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
                                    //JSONObject resp = new JSONObject(response);
                                    //pid = resp.getInt("CustID");
                                    // Toast toast;
                                    Toast toast = Toast.makeText(getApplicationContext(), "Cust Added", Toast.LENGTH_SHORT);
                                    toast.show();
                                    //}
                                   /* catch(Exception e){
                                        Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT);

                                    }
                                }*/
                                } catch (Exception e) {
                                    Toast mtoast = Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_SHORT);
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


            }
        });
        Button placeOrderButton = (Button) findViewById(R.id.placeOrderButton);
        placeOrderButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent tables = new Intent(RequestTableCustomersActivity.this, OrderActivity.class);
                Bundle bundle = new Bundle();
                //bundle.putIntegerArrayList("ids", ids);
                bundle.putInt("custID", custID);
                bundle.putInt("tableID", tableID);
                tables.putExtras(bundle);
                startActivity(tables);
            }


        });

    }
}

