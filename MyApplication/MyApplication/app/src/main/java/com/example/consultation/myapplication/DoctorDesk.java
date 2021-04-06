package com.example.consultation.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.consultation.myapplication.helper.URLs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class DoctorDesk extends AppCompatActivity {

    Button btn6, btn2, btn5;

    InputStream is = null;
    String line = null;
    String result = null;
    private Spinner sp_requests;
    String tmp;

    String[] requestsNameList;
    String[] requestsIDList;
    Context context;

    URLs urLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_desk);

        context = this;

        sp_requests = (Spinner) findViewById(R.id.spinner);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getData();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, requestsNameList);
        sp_requests.setAdapter(adapter);

        btn6 = (Button) findViewById(R.id.button6);

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent2 = new Intent(DoctorDesk.this, SpeechToText.class);
                startActivity(myintent2);
            }
        });

        btn2 = (Button) findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent3 = new Intent(DoctorDesk.this, Dprofile.class);
                startActivity(myintent3);
            }
        });

        btn5 = (Button) findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent4 = new Intent(DoctorDesk.this, chatroom.class);
                String DocID = requestsIDList[sp_requests.getSelectedItemPosition()];
                myintent4.putExtra("rid", DocID);
                startActivity(myintent4);

                    }
                });

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int Docid = pref.getInt("DocId", 0);
        tmp = String.valueOf(Docid);
        TextView did = (TextView) findViewById(R.id.etId);
        did.setText(tmp);

    }

    public void logout(View view) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = getSharedPreferences("Myid", MODE_PRIVATE);
        editor = settings.edit();

        editor.clear();
        editor.commit();

        Intent i = new Intent(DoctorDesk.this, Home.class);
        startActivity(i);
    }

    private void getData() {

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int Docid = pref.getInt("DocId", 0);

        String address = urLs.BASE_URL+"include/messagelist.php?rid=" +Docid;
        try {
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            JSONArray js = new JSONArray(result);
            JSONObject jo = null;

            requestsNameList = new String[js.length()];
            requestsIDList = new String[js.length()];

            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                requestsIDList[i] = jo.getString("users_id");
                requestsNameList[i] = jo.getString("message");
            }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
}

