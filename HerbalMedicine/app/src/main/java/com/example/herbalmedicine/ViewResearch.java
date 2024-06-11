package com.example.herbalmedicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewResearch extends AppCompatActivity implements JsonResponse, AdapterView.OnItemClickListener {


    ListView l1;
    String vals="";
    String[] rid,fname,lname,place,phone,email,val;
    public static String rids;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_research);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        l1 = (ListView) findViewById(R.id.lvresearch);

        l1.setOnItemClickListener(this);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewResearch.this;
        String q = "/Viewresearch";
        q = q.replace(" ", "%20");
        JR.execute(q);



    }
    @Override
    public void response(JSONObject jo) {
        try {

            String method=jo.getString("method");
            if(method.equalsIgnoreCase("Viewresearch")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    rid=new String[ja1.length()];
                    fname=new String[ja1.length()];
                    lname=new String[ja1.length()];
                    place=new String[ja1.length()];
                    phone=new String[ja1.length()];
                    email=new String[ja1.length()];
                    val=new String[ja1.length()];

                    for(int i = 0;i<ja1.length();i++)
                    {
                        rid[i]=ja1.getJSONObject(i).getString("login_id");
                        fname[i]=ja1.getJSONObject(i).getString("first_name");
                        lname[i]=ja1.getJSONObject(i).getString("last_name");
                        place[i]=ja1.getJSONObject(i).getString("place");
                        phone[i]=ja1.getJSONObject(i).getString("phone");
                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Name: "+fname[i]+" "+lname[i]+"\nPlace : "+place[i]+"\nPhone : "+phone[i]+"\nEmail : "+email[i];

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        rids=rid[position];
        SharedPreferences.Editor e=sh.edit();
        e.putString("receiver_id",rids);
        e.commit();
        final CharSequence[] items = {"Chat", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(ViewResearch.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Chat")) {
                    startActivity(new Intent(getApplicationContext(), ChatHere.class));

                }



                else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }

        });
        builder.show();

    }

    public void onBackPressed()
    {

        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Home.class);
        startActivity(b);
    }
}