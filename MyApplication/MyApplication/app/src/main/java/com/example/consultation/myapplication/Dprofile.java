package com.example.consultation.myapplication;

import android.content.SharedPreferences;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.consultation.myapplication.helper.URLs;

import java.util.ArrayList;
import java.util.List;

public class Dprofile extends AppCompatActivity {


    TextView name;
    TextView email;
    TextView age;
    TextView gender;
    TextView contact;
    TextView cnic;
    TextView qualification;

    ParserJSON parserJSON;

    List<List<String>> pharmacy = new ArrayList<>();
    List<String> usernameList = new ArrayList<>();
    List<String> emailList = new ArrayList<String>();
    List<String> ageList = new ArrayList<>();
    List<String> genderList = new ArrayList<String>();
    List<String> contactList = new ArrayList<>();
    List<String> nicList = new ArrayList<String>();
    List<String> quanlificationList = new ArrayList<>();
    List<String> pass = new ArrayList<>();
    List<String> mobList = new ArrayList<>();
    List<String> statusList = new ArrayList<>();

    URLs urLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dprofile);

        name = (TextView) findViewById(R.id.textView11);
        email = (TextView) findViewById(R.id.textView12);
        age = (TextView) findViewById(R.id.textView13);
        gender = (TextView) findViewById(R.id.textView1);
        contact = (TextView) findViewById(R.id.textView2);
        cnic = (TextView) findViewById(R.id.textView3);
        qualification = (TextView) findViewById(R.id.textView4);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid",0);
        int Docid =pref.getInt("DocId", 0);
        String tmp = String.valueOf(Docid);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));
        String address = urLs.BASE_URL+"include/select.php?did=" + tmp;
        parserJSON = new ParserJSON(address);
        pharmacy = parserJSON.parseUprofile();

        usernameList = pharmacy.get(0);
        ageList = pharmacy.get(1);
        genderList = pharmacy.get(2);
        quanlificationList = pharmacy.get(3);
        nicList = pharmacy.get(4);
        emailList = pharmacy.get(5);
        pass = pharmacy.get(6);
        mobList = pharmacy.get(7);
        statusList = pharmacy.get(8);

        name.setText(usernameList.get(0));
        age.setText(ageList.get(0));
        gender.setText(genderList.get(0));
        qualification.setText(quanlificationList.get(0));
        cnic.setText(nicList.get(0));
        email.setText(emailList.get(0));
        contact.setText(mobList.get(0));

    }
}
