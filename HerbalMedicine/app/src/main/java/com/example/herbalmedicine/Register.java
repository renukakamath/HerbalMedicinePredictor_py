package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import org.json.JSONObject;

public class Register extends AppCompatActivity implements JsonResponse {
    EditText e1, e2, e4, e5, e6, e7, e8;
    Button b1;
    String first, last, date, gender, place, phone, email, user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1 = (EditText) findViewById(R.id.etfname);
        e2 = (EditText) findViewById(R.id.etlname);
        e4 = (EditText) findViewById(R.id.etplace);
        e5 = (EditText) findViewById(R.id.etphone);
        e6 = (EditText) findViewById(R.id.etemail);
        e7 = (EditText) findViewById(R.id.etuser);
        e8 = (EditText) findViewById(R.id.etpass);
        b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = e1.getText().toString();
                last = e2.getText().toString();
//                date = e3.getText().toString();
                place = e4.getText().toString();
                phone = e5.getText().toString();
                email = e6.getText().toString();
                user = e7.getText().toString();
                pass = e8.getText().toString();


                if (first.equalsIgnoreCase("")) {
                    e1.setError("Enter your Firstname");
                    e1.setFocusable(true);
                } else if (last.equalsIgnoreCase("")) {
                    e2.setError("Enter your lastname");
                    e2.setFocusable(true);
                }  else if (place.equalsIgnoreCase("")) {
                    e4.setError("Enter your place");
                    e4.setFocusable(true);
                } else if (phone.equalsIgnoreCase("")) {
                    e5.setError("Enter your phoneno");
                    e5.setFocusable(true);
                } else if (email.equalsIgnoreCase("")) {
                    e6.setError("Enter your Email");
                    e6.setFocusable(true);
                } else if (user.equalsIgnoreCase("")) {
                    e7.setError("Enter your username");
                    e7.setFocusable(true);
                } else if (pass.equalsIgnoreCase("")) {
                    e8.setError("Enter your password");
                    e8.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Register.this;
                    String q ="/userregister?fname="+first+"&lname="+last+"&place="+place+"&phone="+phone+"&email="+email+"&username="+user+"&password="+pass;
                    q = q.replace(" ", "%20");
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
                Toast.makeText(getApplicationContext(), "REGISTRATION SUCCESS", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));

            } else if (status.equalsIgnoreCase("duplicate")) {
                startActivity(new Intent(getApplicationContext(), Register.class));
                Toast.makeText(getApplicationContext(), "Username and Password already Exist...", Toast.LENGTH_LONG).show();

            } else {
                startActivity(new Intent(getApplicationContext(), Register.class));

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
        Intent b=new Intent(getApplicationContext(),Login.class);
        startActivity(b);
    }
}