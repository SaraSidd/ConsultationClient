package com.example.consultation.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class chatroom extends AppCompatActivity {

    InputStream is = null;
    String line = null;
    String result = null;
    String userID = null;

    private ArrayList<ModelChat> chatList;

    String address2,success,message2;
    ImageView ivAttachment, ivDownload, buttonSend;
    ListView messagesListView;

    private EditText editTextMessage;
    ArrayAdapter adapter;

    String DocID,PatientID,docID;
    private String sendBy="";

    URLs urLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        //  message = (EditText) findViewById(R.id.editTextMessage);

        ivAttachment.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent5 = new Intent(chatroom.this, com.example.consultation.myapplication.fileUpload.class);
                startActivity(myintent5);
            }
        });

        ivDownload = (ImageView) findViewById(R.id.ivDownload);

        ivDownload.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent4 = new Intent(chatroom.this, down.class);
                startActivity(myintent4);
            }
        });

        messagesListView = (ListView) findViewById(R.id.messages);
        buttonSend = (ImageView) findViewById(R.id.buttonSend);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        ivDownload = (ImageView) findViewById(R.id.ivDownload);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int pat_id = pref.getInt("PatientId", 0);

        SharedPreferences pref2 = getApplicationContext().getSharedPreferences("Myid", 0);
        int doc_id = pref2.getInt("DocId", 0);

        Intent intent = getIntent();
        if (pat_id != 0){
            sendBy="Patient";
            PatientID = String.valueOf(pat_id).toString();
            DocID = intent.getStringExtra("rid");

        } else if (doc_id != 0) {
            sendBy="Doctor";
            PatientID = intent.getStringExtra("rid");
            DocID = String.valueOf(doc_id).toString();
        } else {
            Toast.makeText(this, "Not Detected", Toast.LENGTH_SHORT).show();
        }

        getData();

        if (chatList.size() == 0) {
            Toast.makeText(this, "No Messages", Toast.LENGTH_SHORT).show();
        } else {
            fillListView();
        }

        buttonSend.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendMsg();
                editTextMessage.setText("");
                getData();
                fillListView();
            }
        });
    }

    private  void fillListView() {
        adapter = new ChatAdapter(this, R.layout.chat_item, chatList);
        messagesListView.setAdapter(adapter);
        //messages = null;
    }

    private void getData()
    {
        String address = urLs.BASE_URL+"include/chat/getMessages.php?uid=" + PatientID
                + "&rid=" + DocID;

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

            //messages = new String[js.length()];
            chatList = new ArrayList<>();
            ModelChat modelChat;
            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                modelChat = new ModelChat();
                modelChat.setMesseges(jo.getString("message"));
                modelChat.setSendBy(jo.getString("SendBy"));
                chatList.add(modelChat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendMsg() {

        String u_id = PatientID;
        String r_id = DocID;
        String message = editTextMessage.getText().toString().trim();
        String m = message.toString().replaceAll(" ", "%20");
        String s = getTimeStamp();
        String sentat = s.toString().replaceAll(" ", "%20");

        address2 = urLs.BASE_URL+"include/chat/sendMessage.php?m=" + m
                + "&uid=" + u_id
                + "&rid=" + r_id
                + "&sentat=" + sentat
                + "&sendy=" + sendBy;
        Log.e("a",address2);
        Log.e("message log", m);

        sendMessageData();
        Toast.makeText(this, "Sent", Toast.LENGTH_LONG).show();

    }

    public static String getTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private void sendMessageData() {
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
}
