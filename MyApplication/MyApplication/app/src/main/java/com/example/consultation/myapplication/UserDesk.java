package com.example.consultation.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserDesk extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.desk_user);

        btn1 = (Button) findViewById(R.id.button1);

        btn1.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent1 = new Intent(UserDesk.this, pharmacy.class);
                startActivity(myintent1);
            }
        });

        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(UserDesk.this, Uprofile.class);
                startActivity(myintent2);
            }
        });



        btn3 = (Button) findViewById(R.id.button3);

        btn3.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent3 = new Intent(UserDesk.this, consultation.class);
                startActivity(myintent3);
            }
        });

        btn4 = (Button) findViewById(R.id.button4);

        btn4.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent4 = new Intent(UserDesk.this, DiseaseRecommender.class);
                startActivity(myintent4);
            }
        });

        btn5 = (Button) findViewById(R.id.button5);

        btn5.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent5 = new Intent(UserDesk.this, fileUpload.class);
                startActivity(myintent5);
            }
        });


        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid",0);
        int Patientid =pref.getInt("PatientId", 0);
        String tmp = String.valueOf(Patientid);
        TextView did = (TextView) findViewById(R.id.textView6);
        did.setText(tmp);
    }

    public void logout(View view)
    {

            SharedPreferences settings;
            SharedPreferences.Editor editor;

            settings = getSharedPreferences("Myid", MODE_PRIVATE);
            editor = settings.edit();

            editor.clear();
            editor.commit();

            Intent i = new Intent(UserDesk.this,Home.class);
            startActivity(i);
        
    }
}
