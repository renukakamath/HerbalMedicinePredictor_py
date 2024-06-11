package com.example.herbalmedicine;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class ViewMedicine extends AppCompatActivity implements AdapterView.OnItemClickListener,JsonResponse {

    ListView l1;
    String vals="";
    String[] mid,name,quantity,amount,val,sid;
    public static String mids,qtys,rates,mname,sids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medicine);


        l1 = (ListView) findViewById(R.id.lvmedicine);

        l1.setOnItemClickListener(this);


        JsonReq JR = new JsonReq();
        JR.json_response = (JsonResponse) ViewMedicine.this;
        String q = "/Viewmedicine";
        q = q.replace(" ", "%20");
        JR.execute(q);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mids=mid[i];
        mname=name[i];
        qtys=quantity[i];
        rates=amount[i];
        sids=sid[i];

        final CharSequence[] items = {"Add To Cart", "Cancel"};


        AlertDialog.Builder builder = new AlertDialog.Builder(ViewMedicine.this);
        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Add To Cart")) {
                    startActivity(new Intent(getApplicationContext(), Useraddtocart.class));

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
            if(method.equalsIgnoreCase("Viewmedicine")) {

                String status=jo.getString("status");
                Log.d("pearl",status);


                if(status.equalsIgnoreCase("success")){
                    JSONArray ja1=(JSONArray)jo.getJSONArray("data");
//                    did=new String[ja1.length()];

                    mid=new String[ja1.length()];
                    name=new String[ja1.length()];
                    quantity=new String[ja1.length()];
                    amount=new String[ja1.length()];
                    val=new String[ja1.length()];
                    sid=new String[ja1.length()];
                    for(int i = 0;i<ja1.length();i++)
                    {
                        mid[i]=ja1.getJSONObject(i).getString("medicine_id");
                        name[i]=ja1.getJSONObject(i).getString("medicine_name");
                        quantity[i]=ja1.getJSONObject(i).getString("quantity");
                        amount[i]=ja1.getJSONObject(i).getString("amount");
                        sid[i]=ja1.getJSONObject(i).getString("sales_id");
//                        email[i]=ja1.getJSONObject(i).getString("email");

//                        details[i]=ja1.getJSONObject(i).getString("details");


                        val[i]="Name: "+name[i]+"\nQuantity : "+quantity[i]+"\nAmount : "+amount[i];

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