
//Katheryne's code

package com.example.softwareeng18.food4thought;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    private Button requestWaiterButton;
    private TextView resultText;
    private Button button2;
    private TextView resultText2;
    private EditText tableNumberText;

    public ListView menuView;
    public ArrayAdapter<String> listAdapter;
    ArrayList<String> menuList;
    private String url;
    public static final String TABLENUMBER="tablenumber";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        tableNumberText=(EditText)findViewById(R.id.table_num);
        Bundle bundle=getIntent().getExtras();
        tableNumberText.setText(bundle==null?"1":bundle.getString(TABLENUMBER));
        tableNumberText.setEnabled(false);

        url=getString(R.string.url);
        String menuRequest=url+"/menu";
        menuList=new ArrayList<String>();

        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, menuRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray res=new JSONArray(response);
                    for(int i=0; i<res.length();i++){
                        JSONObject item=res.getJSONObject(i);
                        menuList.add(item.getString("name")+" $"+item.getDouble("price") );
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Spring error: Failed to get menu from spring",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        queue.add(request);

        //customer views wait time for orders
        Button PlaceOrder = (Button) findViewById(R.id.PlaceOrder);
        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to customer screen
                Intent customerIntent=new Intent(OrderActivity.this,CustomerWaitTimeActivity.class);
                startActivity(customerIntent);

            }
        });

        //customer goes to pay bill
        Button PayBill = (Button) findViewById(R.id.PayBill);
        PayBill.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //move to customer screen
                Intent customerIntent=new Intent(OrderActivity.this,PayBillActivity.class);
                startActivity(customerIntent);

            }
        });

        //priority order
        button2 = (Button) findViewById(R.id.Priority2);
        resultText2 = (TextView) findViewById(R.id.result2);
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                showInputDialog2();
            }
        });

        //request waiter
        requestWaiterButton = (Button) findViewById(R.id.RequestWaiter);
        resultText = (TextView) findViewById(R.id.result);
        requestWaiterButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    showInputDialog();
                }
            });





        menuView = (ListView) findViewById( R.id.menuView);
        menuView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listAdapter = new ArrayAdapter<String>(this, R.layout.orderrow, menuList);
        menuView.setAdapter(listAdapter);


//        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                String item = menuList.get(i);
//                menuList.remove(i);
////                menuList.remove(cu)
////                Toast toast = Toast.makeText(getApplicationContext(), ""+menuList.get(i), Toast.LENGTH_SHORT);
////                toast.show();
//                listAdapter.notifyDataSetChanged();
//            }
//        });


        }




        //request waiter
    protected void showInputDialog() {

        LayoutInflater layoutInflater = LayoutInflater.from(OrderActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_call_waiter, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }




        //priority order
    protected void showInputDialog2() {

        LayoutInflater layoutInflater = LayoutInflater.from(OrderActivity.this);
        View promptView = layoutInflater.inflate(R.layout.activity_priority, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderActivity.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext2);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("Yes ($5 fee)", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //resultText2.setText("Hello, " + editText.getText());
                    }
                })
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }


}

