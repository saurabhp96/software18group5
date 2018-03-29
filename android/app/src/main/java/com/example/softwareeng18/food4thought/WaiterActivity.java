package com.example.softwareeng18.food4thought;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        alertsView = (ListView) findViewById( R.id.alerts);

        String[] alertList= new String[] {}; //Querry api to get list

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
        });

    }
}
