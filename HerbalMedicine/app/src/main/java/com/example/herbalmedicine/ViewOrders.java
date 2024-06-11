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

public class ViewOrders extends AppCompatActivity implements JsonResponse {

    ListView l1;
    String vals="";
    String[] oid,odid,amount,date,statuss,val;
    public static String mids;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        l1 = (ListView) findViewById(R.id.lvorders);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewOrders.this;
        String q = "/Vieworders?lid="+sh.getString("log_id","");
        q = q.replace(" ", "%20");
        JR.execute(q);

    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Vieworders")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    oid=new String[ja1.length()];
//                    odid=new String[ja1.length()];
//                    quantity=new String[ja1.length()];
                    amount=new String[ja1.length()];
                    date=new String[ja1.length()];
                    statuss=new String[ja1.length()];
//                    medicine=new String[ja1.length()];
                    val=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {
                        oid[i]=ja1.getJSONObject(i).getString("order_id");
                        amount[i]=ja1.getJSONObject(i).getString("amount");
                        date[i]=ja1.getJSONObject(i).getString("date");
                        statuss[i]=ja1.getJSONObject(i).getString("status");
//                        amount[i]=ja1.getJSONObject(i).getString("amounts");

//                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Amount: "+amount[i]+"\nDate : "+date[i]+"\nStatus : "+statuss[i];
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