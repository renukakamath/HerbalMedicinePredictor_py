package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewCart extends AppCompatActivity implements JsonResponse {

    ListView l1;
    String vals="";
    String[] oid,odid,medicine,quantity,amount,val;
    public static String mids;
    SharedPreferences sh;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        l1 = (ListView) findViewById(R.id.lvcart);
        b1=(Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsonReq JR = new JsonReq();
                JR.json_response = (JsonResponse) ViewCart.this;
                String q = "/Buymedicine?lid="+sh.getString("log_id","");
                q = q.replace(" ", "%20");
                JR.execute(q);
            }
        });
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewCart.this;
        String q = "/Viewcart?lid="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Viewcart")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    oid=new String[ja1.length()];
                    odid=new String[ja1.length()];
                    quantity=new String[ja1.length()];
                    amount=new String[ja1.length()];
                    medicine=new String[ja1.length()];
                    val=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {
                        oid[i]=ja1.getJSONObject(i).getString("order_id");
                        odid[i]=ja1.getJSONObject(i).getString("orderdetail_id");
                        medicine[i]=ja1.getJSONObject(i).getString("medicine_name");
                        quantity[i]=ja1.getJSONObject(i).getString("quantitys");
                        amount[i]=ja1.getJSONObject(i).getString("amounts");

//                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Medicine: "+medicine[i]+"\nQuantity : "+quantity[i]+"\nAmount : "+amount[i];

                    }
                    ArrayAdapter<String> ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,val);
                    l1.setAdapter(ar);
//                    Custimage cc=new Custimage(this,file,workname,workdescription,workdate,workstatus,price);
//                    l.setAdapter(cc);
                }
            }
            if(method.equalsIgnoreCase("Buymedicine")) {

                String status = jo.getString("status");
                Log.d("pearl", status);


                if (status.equalsIgnoreCase("success")) {
                    Toast.makeText(getApplicationContext(), "Buyed Successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ViewCart.class));

                } else {
//                    startActivity(new Intent(getApplicationContext(), Useraddproduct.class));

                    Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
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