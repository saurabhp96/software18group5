package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.Arrays;

public class WaiterActivity extends AppCompatActivity {

    public ListView alertsView ;
    public ArrayAdapter<String> listAdapter ;
    ArrayList<String> current_alerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);
        Button tableAlerts = (Button) findViewById(R.id.tableAlerts);



        tableAlerts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //EditText guests = (EditText) findViewById(R.id.numGuests);
                //String num = guests.getText().toString();
                //int guestsNum = Integer.parseInt(num);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                // String url = "http://192.168.1.222:8080"; //mine
                String url = "http://172.30.20.134:8080";
                url = url+"/seerequests";
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
                                        ArrayList<Integer> tableids= new ArrayList<Integer>();
                                        ArrayList<String> messages = new ArrayList<String>();
                                        for (int i = 0; i< res.length(); i++)
                                        {
                                            JSONObject table = res.getJSONObject(i);
                                            int id = table.getInt("table");
                                            String message = table.getString("message");
                                            tableids.add(id);
                                            messages.add(message);
                                        }
                                        //JSONArray arr = res.getJSONArray("tableId");
                                        //toast = Toast.makeText(getApplicationContext(), "Hello: "+ids.get(0), Toast.LENGTH_LONG);
                                        //toast.show();
                                        Intent tables=new Intent(WaiterActivity.this,WaiterTableAlerts.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putIntegerArrayList("tableids", tableids);
                                        bundle.putStringArrayList("messages",messages);
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
        //alertsView = (ListView) findViewById( R.id.alerts);

        /*String[] alertList= new String[] {}; //Querry api to get list

        current_alerts = new ArrayList<String>();
        current_alerts.addAll( Arrays.asList(alertList) );

        listAdapter = new ArrayAdapter<String>(this, R.layout.orderrow, current_alerts);
        current_alerts.add("Table 1: Need water");
        current_alerts.add("Table 8: Check");
        alertsView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


        alertsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = current_alerts.get(i);
                current_alerts.remove(i);
//                current_alerts.remove(cu)
//                Toast toast = Toast.makeText(getApplicationContext(), ""+current_alerts.get(i), Toast.LENGTH_SHORT);
//                toast.show();
                listAdapter.notifyDataSetChanged();
            }
        });*/

    }
}
