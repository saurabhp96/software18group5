package com.example.softwareeng18.food4thought;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class WaiterTableAlerts extends AppCompatActivity {

    public  ArrayAdapter<String> itemsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_table_alerts);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        Intent intentExtras = getIntent();
        final Bundle extras = intentExtras.getExtras();
        ArrayList<Integer> arr = extras.getIntegerArrayList("tableids");
        ArrayList<String> arr2 = extras.getStringArrayList("messages");
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i<arr.size();i++)
        {
            String message = "Table Num " + arr.get(i) + ": " + arr2.get(i);
            messages.add(message);
        }
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        ListView listView = (ListView) findViewById(R.id.listAlerts);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Mainthis, numbers.get(i), Toast.LENGTH_SHORT).show();


            }
        });

    }

}
