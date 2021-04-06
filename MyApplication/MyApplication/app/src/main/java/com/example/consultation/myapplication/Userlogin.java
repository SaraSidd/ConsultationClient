package com.example.consultation.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Userlogin extends AppCompatActivity {

    Button btn;
    Button btn2;
    EditText username,pas;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = (EditText) findViewById(R.id.etuser);
        pas = (EditText) findViewById(R.id.etpass);
        btn = (Button) findViewById(R.id.btnuser);

        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(Userlogin.this, UserRegister.class);
                startActivity(myintent2);
            }
        });
    }


    public void OnLogin(View view)
    {
        String user = username.getText().toString();
        String password = pas.getText().toString();
        String type = "login";
        BworkerUser backgroundworker = new BworkerUser(this);
        backgroundworker.execute(type,user,password);



    }


}
