package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class Useraddtocart extends AppCompatActivity implements JsonResponse {

    EditText e1,e2,e3,e4,e5;
    Button b1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useraddtocart);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1=(EditText)findViewById(R.id.etplant);
        e2=(EditText)findViewById(R.id.etquantity);
        e3=(EditText)findViewById(R.id.etrate);
        e4=(EditText)findViewById(R.id.etqunatityneed);
        e5=(EditText)findViewById(R.id.ettotal);
        b1=(Button) findViewById(R.id.btBuy);

        e1.setText(ViewMedicine.mname);
        e1.setEnabled(false);
        e2.setText(ViewMedicine.qtys);
        e2.setEnabled(false);
        e3.setText(ViewMedicine.rates);
        e3.setEnabled(false);
        e4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(e4.getText().toString().equalsIgnoreCase(""))
                {

                }
                else if(e4.getText().toString().equalsIgnoreCase("."))
                {
                    e4.setText("");
                }
                else{
                    if(Integer.parseInt(e2.getText().toString())>=Integer.parseInt(e4.getText().toString()))
                    {
                        e5.setText(Integer.parseInt(e4.getText().toString())*Integer.parseInt(e3.getText().toString())+"");
                    }
                    else{
                        e5.setText("");
                        Toast.makeText(getApplicationContext(),"Please enter quantity lesser than current quantity",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e5.getText().toString().equalsIgnoreCase("")) {
                    e4.setError("Enter Quantity");
                    e4.setFocusable(true);
                }
                else{
                    JsonReq JR=new JsonReq();
                    JR.json_response=(JsonResponse)Useraddtocart.this;
                    String q="/medicineaddtocart?lid="+sh.getString("log_id","")+"&mid="+ViewMedicine.mids+"&qty="+e4.getText().toString()+"&amount="+e5.getText().toString()+"&sid="+ViewMedicine.sids;
                    q=q.replace(" ","%20");
                    JR.execute(q);
                }
            }
        });

    }

    @Override
    public void response(JSONObject jo) {

        try {
            String status = jo.getString("status");
            Log.d("pearl", status);


            if (status.equalsIgnoreCase("success")) {
                Toast.makeText(getApplicationContext(), "Add to cart Successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), ViewMedicine.class));

            } else {
                startActivity(new Intent(getApplicationContext(), Useraddproduct.class));

                Toast.makeText(getApplicationContext(), " failed.TRY AGAIN!!", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {

        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),ViewMedicine.class);
        startActivity(b);
    }
}