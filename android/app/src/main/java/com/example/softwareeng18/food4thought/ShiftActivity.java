package com.example.softwareeng18.food4thought;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class ShiftActivity extends AppCompatActivity {


    public ListView shiftsList ;
    public ArrayAdapter<String> shiftAdapter;
    ArrayList<String> empShifts;
    public String role;
    public String empid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift);
        role = getIntent().getStringExtra("role");
        empid = getIntent().getStringExtra("empid");

        shiftsList = (ListView) findViewById(R.id.ListShifts);

        String[] shifts = new String[] {}; //Querry api to get list

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = LoginActivity.BASE_ADDRESS;
        url = url+"/shifts/"+empid;
       // http://172.30.20.134:8080/shifts/2

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray res = new JSONArray(response);
                            for(int i=0;i<res.length();i++){
                                empShifts.add("Start: " + res.getJSONObject(i).getString("startTime")+"\n"+
                                        "End  : " + res.getJSONObject(i).getString("endTime"));
                            }
                            shiftAdapter.notifyDataSetChanged();
//                            Toast.makeText(getApplicationContext(), res.getJSONObject(0).toString(), Toast.LENGTH_LONG).show();
                        }
                        catch(JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Connection Error: "+LoginActivity.BASE_ADDRESS
                        +"/shifts/"+empid+"\nhttp://172.30.20.134:8080/shifts/2", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);


        empShifts = new ArrayList<String>();
        empShifts.addAll( Arrays.asList(shifts) );

        shiftAdapter = new ArrayAdapter<String>(this, R.layout.orderrow, empShifts);
//        empShifts.add("Other");
//        empShifts.add("Night");
        shiftsList.setAdapter(shiftAdapter);
        shiftAdapter.notifyDataSetChanged();


        shiftsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switchTo(role);
            }
        });


    }

    private void switchTo(String role){
        switch(role){
        case "Manager":
            Intent managerIntent=new Intent(ShiftActivity.this,ManagerActivity.class);
            startActivity(managerIntent);
            break;
        case "Chef":
            Intent chefIntent=new Intent(ShiftActivity.this,ChefActivity.class);
            startActivity(chefIntent);
            break;
        case "Waiter":
            Intent waiterIntent=new Intent(ShiftActivity.this,WaiterActivity.class);
            startActivity(waiterIntent);
            break;
        }
    }
}
