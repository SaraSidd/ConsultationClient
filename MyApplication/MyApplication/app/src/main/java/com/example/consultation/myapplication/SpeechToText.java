package com.example.consultation.myapplication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultation.myapplication.helper.URLs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class SpeechToText extends AppCompatActivity {

    String address, message, success;
    InputStream is = null;
    String line = null;
    String result = null;
    Context context;

    TextView resultText;
    Button buttonSave;

    URLs urLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);

        resultText = (TextView)findViewById(R.id.TVresult);
        buttonSave = (Button) findViewById(R.id.button7);

        context = this;

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String medicines = resultText.getText().toString().replaceAll(" ", "%20");
                address = urLs.BASE_URL+"include/stt.php?Medicines=" + medicines;

                getData();

                Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void onButtonClick(View v)
    {
        if(v.getId() == R.id.imageButton)
        {
            promptSpeechInput();
        }

        if(v.getId() == R.id.button7)
        {
            String medicines = resultText.getText().toString().replaceAll(" ", "%20");
            address = urLs.BASE_URL+"include/stt.php?Medicines=" + medicines;

            getData();

            Toast.makeText(context, "Successfully Inserted", Toast.LENGTH_LONG).show();

        }
    }

    public void promptSpeechInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT, "say something!");

        try {
            startActivityForResult(i, 100);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(SpeechToText.this, "sorry your device does not support speech language", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requuest_code, int result_code, Intent i)
    {
        super.onActivityResult(requuest_code, result_code, i);

        switch (requuest_code)
        {
            case 100 : if(result_code == RESULT_OK && i != null);
            {
                ArrayList<String> result = i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                resultText.setText(result.get(0));
            }
            break;
        }
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
