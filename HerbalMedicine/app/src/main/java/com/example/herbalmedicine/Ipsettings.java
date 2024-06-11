package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ipsettings extends AppCompatActivity {
    EditText e1;
    Button b1;
    public static String text;
    SharedPreferences sh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipsettings);
        e1=(EditText)findViewById(R.id.etip);
        b1=(Button)findViewById(R.id.button);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1.setText(sh.getString("ip","192.168"));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text=e1.getText().toString();
                Toast.makeText(getApplicationContext(),"Text:"+text,Toast.LENGTH_LONG).show();
                if(text.equals(""))
                {
                    e1.setText("Enter ip address");
                    e1.setFocusable(true);
                }
                else{
                    SharedPreferences.Editor e=sh.edit();
                    e.putString("ip",text);
                    e.commit();
                    startActivity(new Intent(getApplicationContext(),Login.class));

                }
            }
        });
    }
}