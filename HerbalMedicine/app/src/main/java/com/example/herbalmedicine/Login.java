package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
public class Login extends AppCompatActivity implements JsonResponse {
    EditText e1, e2;
    Button b1;
    TextView t1;
    public static String user, pass, logid, usertype,pname;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = (EditText) findViewById(R.id.etuser);
        e2 = (EditText) findViewById(R.id.etpass);
        b1 = (Button) findViewById(R.id.button);
        t1=(TextView)findViewById(R.id.tvreg);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = e1.getText().toString();
                pass = e2.getText().toString();
                if (user.equalsIgnoreCase("")) {
                    e1.setError("Enter username");
                    e1.setFocusable(true);
                } else if (pass.equalsIgnoreCase("")) {
                    e2.setError("Enter password");
                    e2.setFocusable(true);
                } else {
                    JsonReq JR = new JsonReq();
                    JR.json_response = (JsonResponse) Login.this;
                    String q = "/login?username=" + user + "&password=" + pass;
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
                JSONArray ja1 = (JSONArray) jo.getJSONArray("data");
                logid = ja1.getJSONObject(0).getString("login_id");
//                pname = ja1.getJSONObject(0).getString("first_name")+" "+ja1.getJSONObject(0).getString("last_name");
                usertype = ja1.getJSONObject(0).getString("usertype");


                SharedPreferences.Editor e = sh.edit();
                e.putString("log_id", logid);
//                e.putString("pname", pname);
                e.commit();
                if (usertype.equals("user")) {
                    Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                }
            } else {
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        } catch (Exception e) {
            // TODO: handle exception

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public void onBackPressed()
    {

        super.onBackPressed();
        Intent b=new Intent(getApplicationContext(),Ipsettings.class);
        startActivity(b);
    }

}