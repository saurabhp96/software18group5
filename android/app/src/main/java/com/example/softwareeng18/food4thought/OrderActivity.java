
//Katheryne's code

package com.example.softwareeng18.food4thought;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

    private Button request;
   // private TextView resultText;
    //private Button button2;
    //private TextView resultText2;
    String message = " ";
    int tableID = 0;

    private Button requestWaiterButton;
    private TextView resultText;
    private Button button2;
    private TextView resultText2;
    private EditText tableNumberText;
    final Context context=this;

    public ListView menuView;
    public ArrayAdapter<String> listAdapter;
    ArrayList<String> menuList;
    private String url;
    public static final String TABLENUMBER="tablenumber";


    public String Jsonoutput;

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
                    Jsonoutput = response;
                    for(int i=0; i<res.length();i++){
                        JSONObject item=res.getJSONObject(i);
                        menuList.add(item.getString("itemName")+" $"+item.getDouble("price") );
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

        Button requestButton = (Button) findViewById(R.id.RequestWaiter);
        resultText = (TextView) findViewById(R.id.result);
        /*requestWaiterButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View view) {
                    showInputDialog();
                }
            });*/
        requestButton = (Button) findViewById(R.id.RequestWaiter);
        resultText = (TextView) findViewById(R.id.result);
        requestButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intExtras = getIntent();
                Bundle extras = intExtras.getExtras();
                int custID = extras.getInt("custID");
                tableID = extras.getInt("tableID");

                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setTitle("Send message to waiter");

// Set up the input
                final EditText input = new EditText(OrderActivity.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        message = input.getText().toString();
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                        // String url = "http://192.168.1.222:8080";
                        String url = "http://172.30.20.134:8080";
                        Toast toast = Toast.makeText(OrderActivity.this,"msg: "+message, Toast.LENGTH_LONG);
                        toast.show();
                        url = url + "/addrequest?tableID=" + tableID + "&message=" + message;
                        //Toast toast = Toast.makeText(OrderActivity.this,"msg: "+message, Toast.LENGTH_LONG);
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
                                                //Toast toast;
                                            /*JSONArray res = new JSONArray(response);
                                            ArrayList<Integer> ids= new ArrayList<Integer>();
                                            for (int i = 0; i< res.length(); i++)
                                            {
                                                JSONObject table = res.getJSONObject(i);
                                                int id = table.getInt("tableId");
                                                ids.add(id);
                                            }*/
                                                //JSONArray arr = res.getJSONArray("tableId");
                                                //toast = Toast.makeText(getApplicationContext(), "Hello: "+ids.get(0), Toast.LENGTH_LONG);
                                                //toast.show();
                                           /* Intent tables=new Intent(RequestTableActivity.this,RequestTableCustomersActivity.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putIntegerArrayList("ids", ids);
                                            bundle.putInt("custID", pid);
                                            tables.putExtras(bundle);
                                            startActivity(tables);*/
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
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                //showInputDialog();




            }
        });




        menuView = (ListView) findViewById( R.id.menuView);
        //menuView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listAdapter = new ArrayAdapter<String>(this, R.layout.orderrow, menuList);
        menuView.setAdapter(listAdapter);



        //select menu item
        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //http://www.mkyong.com/android/android-prompt-user-input-dialog-example/
                LayoutInflater li=LayoutInflater.from(context);
                View previewView=li.inflate(R.layout.preview_display,null);
                final int pos=position;

                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setView(previewView);

                ImageView itemImage=(ImageView)previewView.findViewById(R.id.preview_image);
                TextView itemName=(TextView)previewView.findViewById(R.id.preview_name);
                TextView itemPrice=(TextView)previewView.findViewById(R.id.preview_price);

                try {
                    Toast.makeText(context, menuList.get(position), Toast.LENGTH_LONG).show();
                    //JSONObject root=new JSONObject();
                    //JSONArray reader = root.getJSONArray(Jsonoutput);
                    //JSONObject c = reader.getJSONObject(position);
                    //String imageURL=c.getString("image");
                    //new ImageLoader(itemImage).execute(imageURL);

                    //Toast.makeText(context, Jsonoutput, Toast.LENGTH_LONG).show();

                    JSONObject root=new JSONObject(Jsonoutput);
                    //Toast.makeText(context, root.toString(), Toast.LENGTH_LONG).show();

                    JSONArray array = new JSONArray(root);
                    //Toast.makeText(context, array.toString(), Toast.LENGTH_LONG).show();
                    //JSONObject c = reader.getJSONObject(position);

                    Toast.makeText(context, array.getJSONObject(position).toString(), Toast.LENGTH_LONG).show();
                    Log.d("tag",array.getJSONObject(position).toString() );

                    List<String> list = new ArrayList<String>();
                    for (int i=0; i<array.length(); i++) {
                        list.add( array.getString(i) );
                    }

                    Toast.makeText(context, list.toString(), Toast.LENGTH_LONG).show();



                    //itemName.setText(array[position].getString("itemName"));
                    //itemPrice.setText("$" + c.getString("price"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        okPressed(pos);
                    }
                }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                builder.create().show();

            }
        });



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

    //okpressed: add to list which becomes total Order
    private void okPressed(int position) {
//        txtString.setText(Long.toString(position));
//        int idnum = position;
//        String images = "";
//        String instructions = "";
//        String title="";
//        String recipeId = "";
//
//        // Parse JSON return
//        try {
//            JSONObject jsonroot = new JSONObject(Jsonoutput);
//            JSONArray reader = jsonroot.getJSONArray("results");
//            JSONObject c = reader.getJSONObject(idnum);
//            images = c.getString("image");
//            title = c.getString("title");
//            recipeId = c.getString("id");
//
//            JSONArray instructionlist = c.getJSONArray("analyzedInstructions").getJSONObject(0).getJSONArray("steps");
//            for (int j = 0; j < instructionlist.length(); j++) {
//                int tmp = j + 1;
//                instructions = instructions + "Step " + tmp + ": " + instructionlist.getJSONObject(j).getString("step") + " \n \n";
//            }
//
//            JSONArray usedIngredients=c.getJSONArray("usedIngredients");
//            for(int index=0; index<usedIngredients.length(); index++){
//                JSONObject ingredientJSON=usedIngredients.getJSONObject(index);
//                Ingredient ing=new Ingredient(ingredientJSON.getString("name"),ingredientJSON.getDouble("amount"),ingredientJSON.getString("unit"));
//                if (pantryIngredients.contains(ing)) {
//                    int pantryI = pantryIngredients.indexOf(ing);
//                    double newAmount = pantryIngredients.get(pantryI).getQuantity() -
//                            ingredientJSON.getDouble("amount") *
//                                    findConversionFactor(ingredientJSON.getString("unit"), pantryIngredients.get(pantryI).getMeasurementUnit());
//                    pantryIngredients.get(pantryI).setQuantity(newAmount);
//                    if (pantryIngredients.get(pantryI).getQuantity() <= 0) {
//                        pantryIngredients.remove(pantryI);
//                    }
//                }
//            }
//
//            updatePantry(pantryIngredients);
//
//        } catch (JSONException e) {
//            txtString.setText("fail Json parse");
//        }
//
//        Intent intent = new Intent(Recipes.this, Recipe_Display.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("IMAGE_URL", images);
//        bundle.putString("RECIPE_NAME",title);
//        bundle.putString("INSTRUCTIONS", instructions);
//        bundle.putString("RECIPE_ID", recipeId);
//
//        //txtString.setText(bundle.getString("INSTRUCTIONS"));
//        if (intent == null)
//            txtString.setText("fail intent");
//        intent.putExtras(bundle);
//        startActivity(intent);

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

