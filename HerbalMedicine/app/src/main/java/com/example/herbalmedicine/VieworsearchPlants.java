package com.example.herbalmedicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class VieworsearchPlants extends AppCompatActivity implements AdapterView.OnItemClickListener,JsonResponse {

    EditText e1;
    ListView l1;
    String vals="";
    String[] plant,quantity,amount,pid,val;
    public static String pids,plants,qtys,rates;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworsearch_plants);
        e1=(EditText)findViewById(R.id.etsearch);
        l1 = (ListView) findViewById(R.id.lvplants);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1.setOnItemClickListener(this);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) VieworsearchPlants.this;
        String q = "/Viewplants";
        q = q.replace(" ", "%20");
        JR.execute(q);

        e1.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) VieworsearchPlants.this;
                String q = "/Serachplants?serached="+e1.getText().toString();
                q = q.replace(" ", "%20");
                JR.execute(q);


            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        pids=pid[i];
        plants =plant[i];
        qtys=quantity[i];
        rates=amount[i];

        final CharSequence[] items = {"Purchase", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(VieworsearchPlants.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Purchase")) {
                     startActivity(new Intent(getApplicationContext(), Useraddproduct.class));

                }



                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Viewplants")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    pid=new String[ja1.length()];
                    plant=new String[ja1.length()];
                    quantity=new String[ja1.length()];
                    amount=new String[ja1.length()];
                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        pid[i]=ja1.getJSONObject(i).getString("plantsale_id");
                        plant[i]=ja1.getJSONObject(i).getString("plant");
                        quantity[i]=ja1.getJSONObject(i).getString("quantity");
                        amount[i]=ja1.getJSONObject(i).getString("amount");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Plant: "+plant[i]+"\nQuantity : "+quantity[i]+"\nAmount : "+amount[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
                    l1.setAdapter(ar);
//                    Custimage cc=new Custimage(this,file,workname,workdescription,workdate,workstatus,price);
//                    l.setAdapter(cc);
                }
            }
        }
        catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }



    }

    public void onBackPressed()
    {

        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Home.class);
        startActivity(b);
    }
}