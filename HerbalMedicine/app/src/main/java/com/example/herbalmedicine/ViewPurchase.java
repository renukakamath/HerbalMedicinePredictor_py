package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewPurchase extends AppCompatActivity implements JsonResponse {

    ListView l1;
    String vals="";
    String[] pid,plant,quantity,amount,date,statuss,val;
    public static String mids;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_purchase);

        l1 = (ListView) findViewById(R.id.lvpurchase);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewPurchase.this;
        String q = "/Viewpurchase?lid="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Viewpurchase")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    pid=new String[ja1.length()];
                    plant=new String[ja1.length()];
                    quantity=new String[ja1.length()];
                    amount=new String[ja1.length()];
                    date=new String[ja1.length()];
                    statuss=new String[ja1.length()];
                    val=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {
                        pid[i]=ja1.getJSONObject(i).getString("purchase_id");
                        plant[i]=ja1.getJSONObject(i).getString("plant");
                        quantity[i]=ja1.getJSONObject(i).getString("quantitys");
                        amount[i]=ja1.getJSONObject(i).getString("amounts");
                        statuss[i]=ja1.getJSONObject(i).getString("status");
//                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Plant: "+plant[i]+"\nQuantity : "+quantity[i]+"\nAmount : "+amount[i]+"\nStatus : "+statuss[i];

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