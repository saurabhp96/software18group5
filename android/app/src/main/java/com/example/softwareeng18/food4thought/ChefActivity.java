package com.example.softwareeng18.food4thought;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;

public class ChefActivity extends AppCompatActivity {

    public ListView ordersList ;
    public ArrayAdapter<MenuItem> listAdapter ;
    ArrayList<MenuItem> current_orders;

//    public RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        ordersList = (ListView) findViewById( R.id.listOrders);

        MenuItem[] orders= new MenuItem[] {};

        //Query api to get list
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = LoginActivity.BASE_ADDRESS;
        url = url+"/unprepared";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray res = new JSONArray(response);
                            for(int i=0;i<res.length();i++){
                                MenuItem mi = new MenuItem(res.getJSONArray(i).get(0).toString(),
                                        res.getJSONArray(i).get(1).toString());
                                if(!current_orders.contains(mi)){
                                    current_orders.add(mi);
                                }
                            }
                            listAdapter.notifyDataSetChanged();
//                            shiftAdapter.notifyDataSetChanged();
//                            Toast.makeText(getApplicationContext(), res.getJSONArray(0).get(0).toString()+": "+
//                                    res.getJSONArray(0).get(1).toString(), Toast.LENGTH_LONG).show();
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);



        //set adapter and populate listview with test items
        current_orders = new ArrayList<MenuItem>();
        current_orders.addAll( Arrays.asList(orders) );

        listAdapter = new ArrayAdapter<MenuItem>(this, R.layout.orderrow, current_orders);
//        current_orders.add(new MenuItem("Alfredo Pasta"));
//        current_orders.add(new MenuItem("Spicy Sushi"));
        ordersList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = current_orders.get(i);
                markPrepared(current_orders.get(i));
                current_orders.remove(i);
//                current_orders.remove(cu)
//                Toast toast = Toast.makeText(getApplicationContext(), ""+current_orders.get(i), Toast.LENGTH_SHORT);
//                toast.show();
                listAdapter.notifyDataSetChanged();
            }
        });

    }

    public void markPrepared(MenuItem m){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = LoginActivity.BASE_ADDRESS;
        url = url+"/prepared?custID="+m.custID+ "&menuItem="+m.menuItem;
//        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();
        Log.d("post",url);


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Removed Item", Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Remove Item POST Error", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

}
