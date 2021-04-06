package com.example.consultation.myapplication;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegister extends Activity {

    String address, message, success;
    InputStream is = null;
    String line = null;
    String result = null;
    Context context;

    EditText editTextName;
    TextView editTextAge;
    EditText editTextGender;
    EditText editTextMobile;
    EditText editTextAddress;
    EditText editTextCnic;
    EditText editTextEmail;
    EditText editTextPassword;

    Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        context = this;
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextCnic = (EditText) findViewById(R.id.editTextCnic);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        editTextAge = (TextView) findViewById(R.id.editTextAge);

        editTextAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpDateDialog mainActivity = new PopUpDateDialog(v);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                mainActivity.show(ft, "DatePicker");
            }
        });

        buttonRegister = (Button) findViewById(R.id.button);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String ed1=editTextMobile.getText().toString();
                int size=ed1.length();

                String ed2=editTextCnic.getText().toString();
                int size2=ed2.length();

                String ed3=editTextAge.getText().toString();
                int size3=ed3.length();
                String ed4=editTextGender.getText().toString();
                int size4=ed4.length();
                String ed5=editTextAddress.getText().toString();
                int size5=ed5.length();
                String ed6=editTextPassword.getText().toString();
                int size6=ed6.length();
                String ed7=editTextName.getText().toString();
                int size7=ed7.length();


                if (size!=11){
                    Toast.makeText(context, "Insert correct 11 digits in contact", Toast.LENGTH_LONG).show();

                }
                else if (size2!=13){
                    Toast.makeText(context, "Insert correct 13 digits in CNIC", Toast.LENGTH_LONG).show();

                }
                else if (!emailValidator(editTextEmail.getText().toString())){
                    Toast.makeText(context, "Incorrect Email.!", Toast.LENGTH_LONG).show();
                }

                else if (size3==0 || size4==0 || size5==0 || size6==0 || size7==0){
                    Toast.makeText(context, "Please Fill all Fields", Toast.LENGTH_LONG).show();

                }

                else {

                    address = "http://192.168.8.101/ocs.ebs-agro-portal.com/include/register.php?Name=" + editTextName.getText().toString()
                            + "&Age=" + editTextAge.getText().toString()
                            + "&Gender=" + editTextGender.getText().toString()
                            + "&Mobile=" + editTextMobile.getText().toString()
                            + "&Address=" + editTextAddress.getText().toString()
                            + "&Cnic=" + editTextCnic.getText().toString()
                            + "&Email=" + editTextEmail.getText().toString()
                            + "&Password=" + editTextPassword.getText().toString();

                    getData();

                    Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void getData() {
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

            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                success = jo.getString("success");
                message = jo.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
