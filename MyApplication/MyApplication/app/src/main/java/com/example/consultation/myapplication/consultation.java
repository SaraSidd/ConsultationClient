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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.consultation.myapplication.helper.URLs;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class consultation extends AppCompatActivity {

    InputStream is = null;
    String line = null;
    String result = null;
    private Spinner sp_doctors;

    String[] doctorsNameList;
    String[] doctorsIDList;


    Button btnsend,btnchat;
    EditText message;
    String address2,success,message2;
    Context context;

    URLs urLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        context = this;

        message = (EditText) findViewById(R.id.editText);
        btnsend = (Button) findViewById(R.id.button3);

        sp_doctors = (Spinner) findViewById(R.id.spinner3);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        getDoctorData();

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, doctorsNameList);
        sp_doctors.setAdapter(adapter);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int Patientid = pref.getInt("PatientId", 0);
        final String patid = String.valueOf(Patientid);


        btnsend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String medicines = message.getText().toString().replaceAll(" ", "%20");
                String patientid = patid.toString();
                String doctor_id = doctorsIDList[sp_doctors.getSelectedItemPosition()];

                String s = getTimeStamp();
                String sentat = s.toString().replaceAll(" ", "%20");


                address2 = urLs.BASE_URL+"include/request.php?Medicines=" + medicines
                        + "&PatientId=" + patientid
                        + "&DoctorId=" + doctor_id
                        + "&sentat=" + sentat
                ;
                getData();
                Toast.makeText(context, "request has been sent", Toast.LENGTH_LONG).show();
            }
        });

        btnchat = (Button) findViewById(R.id.button4);
        btnchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cr = new Intent(consultation.this,chatroom.class);
                String DocID = doctorsIDList[sp_doctors.getSelectedItemPosition()];
                cr.putExtra("rid", DocID);
                startActivity(cr);
            }
        });

    }
    //This method will return current timestamp
    public static String getTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private void getData()
    {
        try {
            URL url = new URL(address2);
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

            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                success = jo.getString("success");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getDoctorData()
    {
        String address = urLs.BASE_URL+"include/doctorlist.php";
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

            doctorsNameList = new String[js.length()];
            doctorsIDList = new String[js.length()];

            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                doctorsIDList[i] = jo.getString("Docid");
                doctorsNameList[i] = jo.getString("Name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
