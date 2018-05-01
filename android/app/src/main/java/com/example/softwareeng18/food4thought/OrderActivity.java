
//Katheryne's code

package com.example.softwareeng18.food4thought;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
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
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    private class MenuItem{
        String name;
        double price;
        int calories;

        public MenuItem(String name, double price, int calories) {
            this.name = name;
            this.price = price;
            this.calories = calories;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCalories() {
            return calories;
        }

        public void setCalories(int calories) {
            this.calories = calories;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private Button requestWaiterButton;
    private TextView resultText;
    private Button button2;
    private TextView resultText2;
    private EditText tableNumberText;
    private int customerID;
    final Context context=this;

    public ListView menuView;
    private List<MenuItem> menuList;
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
        menuList=new ArrayList<MenuItem>();

        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, menuRequest, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray res=new JSONArray(response);

                    for(int i=0; i<res.length();i++){
                        JSONObject item=res.getJSONObject(i);
                        menuList.add(new MenuItem(item.getString("itemName"),item.getDouble("price"),item.getInt("calories")) );
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
                for(int i=0; i<menuList.size(); i++) {
                    CheckedTextView checkedTextView=(CheckedTextView)menuView.getChildAt(i);
                    if(checkedTextView.isChecked()) {
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        String addRequest = url + "/order/" + customerID + "?itemName="+menuList.get(i).getName();
                        StringRequest request = new StringRequest(Request.Method.POST, addRequest, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                        queue.add(request);
                    }
                }

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

        ArrayAdapter<MenuItem> listAdapter = new ArrayAdapter<MenuItem>(this, android.R.layout.simple_list_item_multiple_choice, menuList);
        menuView.setAdapter(listAdapter);


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

