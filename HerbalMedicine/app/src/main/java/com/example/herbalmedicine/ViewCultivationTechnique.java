package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewCultivationTechnique extends AppCompatActivity implements JsonResponse {

    ListView l1;
    String vals="";
    String[] mid,plant,details,val;
    public static String mids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cultivation_technique);

        l1 = (ListView) findViewById(R.id.lvtechniques);

//        l1.setOnItemClickListener(this);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewCultivationTechnique.this;
        String q = "/Viewcultivation";
        q = q.replace(" ", "%20");
        JR.execute(q);
    }

    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Viewcultivation")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

//                    mid=new String[ja1.length()];
                    plant=new String[ja1.length()];
                    details=new String[ja1.length()];
                    val=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {
//                        mi/d[i]=ja1.getJSONObject(i).getString("medicine_id");
                        plant[i]=ja1.getJSONObject(i).getString("plant");
                        details[i]=ja1.getJSONObject(i).getString("details");
//                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Plant: "+plant[i]+"\nDetails : "+details[i];

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