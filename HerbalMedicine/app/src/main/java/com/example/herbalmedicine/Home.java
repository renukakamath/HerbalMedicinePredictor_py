package com.example.herbalmedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    TextView t1;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        t1=(TextView) findViewById(R.id.textView3);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button1);
        b3=(Button)findViewById(R.id.button2);
        b4=(Button)findViewById(R.id.button3);
        b5=(Button)findViewById(R.id.button4);
        b6=(Button)findViewById(R.id.button5);
        b7=(Button)findViewById(R.id.button6);
        b8=(Button)findViewById(R.id.button7);
        b9=(Button)findViewById(R.id.button8);
        b10=(Button)findViewById(R.id.button9);
        b11=(Button)findViewById(R.id.button10);

//        t1.setText("Welcome "+sh.getString("pname",""));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewResearch.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),VieworsearchPlants.class));

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewCultivationTechnique.class));
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Vieworserchdoctors.class));
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewBookings.class));
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Feedbacks.class));
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewMedicine.class));
            }
        });
        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewCart.class));
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewOrders.class));
            }
        });
        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ViewPurchase.class));
            }
        });

    }

    public void onBackPressed()
	{

			super.onBackPressed();
			Intent b=new Intent(getApplicationContext(),Home.class);
			startActivity(b);
		}
}