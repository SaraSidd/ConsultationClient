package com.example.consultation.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Button btn2;

    EditText username,pas;
    Spinner spinner;
    String[] LoginOptions = { "User", "Doctor", "Admin" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, LoginOptions);



        username = (EditText) findViewById(R.id.etusername);
        pas = (EditText) findViewById(R.id.etpassword);
        btn = (Button) findViewById(R.id.btnlogin);

       /* btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("owais") && pas.getText().toString().equals("khan"))
                {
                    Toast.makeText(getApplicationContext(),"Login Success", Toast.LENGTH_LONG);
                    Intent myintent = new Intent(MainActivity.this, UserDesk.class);
                    startActivity(myintent);
                }
            }
        });*/

        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(MainActivity.this, Register.class);
                startActivity(myintent2);
            }
        });


    }
    public void OnLogin(View view)
    {
        String user = username.getText().toString();
        String password = pas.getText().toString();
        String type = "login";
        BackgroundWorker backgroundworker = new BackgroundWorker(this);
        backgroundworker.execute(type,user,password);

    }
}
