package com.example.softwareeng18.food4thought;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ChefActivity extends AppCompatActivity {

    public ListView ordersList ;
    public ArrayAdapter<String> listAdapter ;
    ArrayList<String> current_orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef);

        ordersList = (ListView) findViewById( R.id.listOrders);

        String[] orders= new String[] {}; //Querry api to get list

        current_orders = new ArrayList<String>();
        current_orders.addAll( Arrays.asList(orders) );

        listAdapter = new ArrayAdapter<String>(this, R.layout.orderrow, current_orders);
        current_orders.add("Alfredo Pasta");
        current_orders.add("Spicy Sushi");
        ordersList.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();


        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String item = current_orders.get(i);
                current_orders.remove(i);
//                current_orders.remove(cu)
//                Toast toast = Toast.makeText(getApplicationContext(), ""+current_orders.get(i), Toast.LENGTH_SHORT);
//                toast.show();
                listAdapter.notifyDataSetChanged();
            }
        });


//        Button manager = (Button) findViewById(R.id.done);
//        manager.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast toast = Toast.makeText(getApplicationContext(), ""+ordersList.getSelectedItem(), Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });

    }

}
