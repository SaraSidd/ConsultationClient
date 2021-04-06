package com.example.consultation.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void doctor(View view)
    {
        Intent dd = new Intent(Home.this,MainActivity.class);
        startActivity(dd);
    }


    public void patient(View view)
    {
        Intent pt = new Intent(Home.this,Userlogin.class);
        startActivity(pt);
    }
}
