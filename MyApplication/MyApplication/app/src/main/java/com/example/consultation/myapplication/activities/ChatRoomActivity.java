package com.example.consultation.myapplication.activities;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import com.example.consultation.myapplication.R;
import com.example.consultation.myapplication.down;
import com.example.consultation.myapplication.gcm.GCMRegistrationIntentService;
import com.example.consultation.myapplication.helper.AppController;
import com.example.consultation.myapplication.helper.Constants;
import com.example.consultation.myapplication.helper.Message;
import com.example.consultation.myapplication.helper.ThreadAdapter;
import com.example.consultation.myapplication.helper.URLs;

import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.HashMap;
import java.util.Map;

public class ChatRoomActivity extends AppCompatActivity implements View.OnClickListener {

    //Broadcast receiver to receive broadcasts
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    //Progress dialog
    private ProgressDialog dialog;

    //Recyclerview objects
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    //ArrayList of messages to store the thread messages
    private ArrayList<Message> messages;

    //Button to send new message on the thread
    private Button buttonSend;

    //EditText to send new message on the thread
    private EditText editTextMessage;

    EditText message;
    String address2,success,message2;
    ImageView ivAttachment, ivDownload;

    InputStream is = null;
    String line = null;
    String result = null;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);

        context = this;


        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
      //  message = (EditText) findViewById(R.id.editTextMessage);

        ivAttachment.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent5 = new Intent(ChatRoomActivity.this, fileUpload.class);
                startActivity(myintent5);
            }
        });


        ivDownload = (ImageView) findViewById(R.id.ivDownload);

        ivDownload.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent4 = new Intent(ChatRoomActivity.this, down.class);
                startActivity(myintent4);
            }
        });


        //Adding toolbar to activity
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(AppController.getInstance().getUserName());
 //       setSupportActionBar(toolbar);

        //Displaying dialog while the chat room is being ready
        dialog = new ProgressDialog(this);
        dialog.setMessage("Opening chat room");
        dialog.show();

        //Initializing recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //Initializing message arraylist
        messages = new ArrayList<>();

        //Calling function to fetch the existing messages on the thread
        fetchMessages();

        //initializing button and edittext
        buttonSend = (Button) findViewById(R.id.buttonSend);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        //Adding listener to button
        buttonSend.setOnClickListener(this);

        //Creating broadcast receiver
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_SUCCESS)) {

                    //When gcm registration is success do something here if you need

                } else if (intent.getAction().equals(GCMRegistrationIntentService.REGISTRATION_TOKEN_SENT)) {

                    //When the registration token is sent to ther server displaying a toast
                    Toast.makeText(getApplicationContext(), "Chatroom Ready...", Toast.LENGTH_SHORT).show();

                    //When we received a notification when the app is in foreground
                } else if (intent.getAction().equals(Constants.PUSH_NOTIFICATION)) {
                    //Getting message data
                    String name = intent.getStringExtra("name");
                    String message = intent.getStringExtra("message");
                    String id = intent.getStringExtra("id");
                   // String reciever_id = intent.getStringExtra("reciever_id");

                    //processing the message to add it in current thread
                    processMessage(name, message, id);
                }
            }
        };

        //if the google play service is not in the device app won't work
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());

        if (ConnectionResult.SUCCESS != resultCode) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                Toast.makeText(getApplicationContext(), "Google Play Service is not install/enabled in this device!", Toast.LENGTH_LONG).show();
                GooglePlayServicesUtil.showErrorNotification(resultCode, getApplicationContext());

            } else {
                Toast.makeText(getApplicationContext(), "This device does not support for Google Play Service!", Toast.LENGTH_LONG).show();
            }
        } else {
            Intent itent = new Intent(this, GCMRegistrationIntentService.class);
            startService(itent);
        }
    }

    //This method will fetch all the messages of the thread
    private void fetchMessages() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_FETCH_MESSAGES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();

                        try {
                            JSONObject res = new JSONObject(response);
                            JSONArray thread = res.getJSONArray("messages");
                            for (int i = 0; i < thread.length(); i++) {
                                JSONObject obj = thread.getJSONObject(i);
                                int userId = obj.getInt("userid");
                            //    int recieverId = obj.getInt("recieverid");
                                String message = obj.getString("message");
                                String name = obj.getString("name");
                                String sentAt = obj.getString("sentat");
                                Message messagObject = new Message(userId, message, sentAt, name);
                                messages.add(messagObject);
                            }

                            adapter = new ThreadAdapter(ChatRoomActivity.this, messages, AppController.getInstance().getUserId());
                            recyclerView.setAdapter(adapter);
                            scrollToBottom();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    //Processing message to add on the thread
    private void processMessage(String name, String message, String id) {
        Message m = new Message(Integer.parseInt(id), message, getTimeStamp(), name);
        messages.add(m);
        scrollToBottom();
    }

    //This method will send the new message to the thread
    private void sendMessage() {
        final String message = editTextMessage.getText().toString().trim();
        if (message.equalsIgnoreCase(""))
            return;
        int userId = AppController.getInstance().getUserId();
      //  int recieverId = AppController.getInstance().getRecieverId();
        String name = AppController.getInstance().getUserName();
        String sentAt = getTimeStamp();

        Message m = new Message(userId, message, sentAt, name);
        messages.add(m);
        adapter.notifyDataSetChanged();

        scrollToBottom();

        editTextMessage.setText("");
        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int Docid = pref.getInt("DocId", 0);
        final String tmp = String.valueOf(Docid);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_SEND_MESSAGE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", tmp);//String.valueOf(AppController.getInstance().getUserId()));
//                params.put("reciever_id", String.valueOf(AppController.getInstance().getRecieverId()));
                params.put("message", message);
                params.put("name", AppController.getInstance().getUserName());
                return params;
            }
        };

        //Disabling retry to prevent duplicate messages
        int socketTimeout = 0;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

    //method to scroll the recyclerview to bottom
    private void scrollToBottom() {
        adapter.notifyDataSetChanged();
        if (adapter.getItemCount() > 1)
            recyclerView.getLayoutManager().smoothScrollToPosition(recyclerView, null, adapter.getItemCount() - 1);
    }

    //This method will return current timestamp
    public static String getTimeStamp() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    //Registering broadcast receivers
    @Override
    protected void onResume() {
        super.onResume();
        Log.w("MainActivity", "onResume");
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_SUCCESS));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_ERROR));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(GCMRegistrationIntentService.REGISTRATION_TOKEN_SENT));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Constants.PUSH_NOTIFICATION));
    }


    //Unregistering receivers
    @Override
    protected void onPause() {
        super.onPause();
        Log.w("MainActivity", "onPause");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
    }



/*    SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
    int Docid = pref.getInt("DocId", 0);
    final String tmp = String.valueOf(Docid);


//    PreferenceManager.getDefaultSharedPreferences(context).getString("MYLABEL", );
    SharedPreferences pref1 = getApplicationContext().getSharedPreferences("Myid", 0);
    int Patid = pref1.getInt("PatientId", 0);
    final String tmp1 = String.valueOf(Patid);
*/

    //Sending message onclick


    @Override
    public void onClick(View v) {
        if (v == buttonSend)
            sendMessage();

        Intent intent = getIntent();
         String idp = intent.getStringExtra("str");

        message = (EditText) findViewById(R.id.editTextMessage);
        String medicines = message.getText().toString().replaceAll(" ", "%20");
  //      String patientid = tmp1.toString();
    //    String doctorid = tmp.toString();

       // String getpos = doctorId.get(sp_doctors.getSelectedItemPosition()).toString();

        address2 = "http://10.0.2.2/ocs.ebs-agro-portal.com/include/request.php?Medicines=" + medicines
                + "&PatientId=" + idp//patientid
                + "&DoctorId=3"; //+ doctorid;
        getData();
//        Toast.makeText(context, patientid+"", Toast.LENGTH_LONG).show();




        }

    //Creating option menu to add logout feature
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Adding our menu to toolbar
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Adding logout option here
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuLogout) {
            AppController.getInstance().logout();
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
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
                message2 = jo.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}