package com.example.consultation.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;

import com.example.consultation.myapplication.helper.URLs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;



public class BackgroundWorker extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;
    URLs urLs;

    BackgroundWorker (Context ctx)
    {

        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = urLs.BASE_URL+"functions.php";
        if (type.equals("login"))
        {
            try {
                String user_name = params[1];
                String pswrd = params[2];
                URL url = new URL(login_url);
                HttpURLConnection huc = (HttpURLConnection)url.openConnection();
                huc.setRequestMethod("POST");
                huc.setDoOutput(true);
                huc.setDoInput(true);
                OutputStream outputstream = huc.getOutputStream();
                BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(outputstream,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pswrd,"UTF-8");

                bufferedwriter.write(post_data);
                bufferedwriter.flush();
                bufferedwriter.close();
                outputstream.close();
                InputStream inputstream = huc.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputstream,"iso-8859-1"));
                String result="";
                String line="";
                while ((line = bufferedReader.readLine())!= null)
                {
                    result+=line;
                }

                bufferedReader.close();
                inputstream.close();
                huc.disconnect();
                return result;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute()
    {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        int result1 = Integer.parseInt(result);
        if (result1 == 0) {
            alertDialog.setMessage("incorrect email or password");
            alertDialog.show();
        } else {


            //alertDialog.setMessage(result);
            //alertDialog.show();
            SharedPreferences pref = context.getSharedPreferences("Myid", 0);
            Editor editor = pref.edit();
            editor.putInt("DocId", result1);
            editor.apply();
            int Docid =pref.getInt("DocId", 0);

            Intent i = new Intent(this.context,DoctorDesk.class);
            context.startActivity(i);
            String tmpStr10 = String.valueOf(Docid);

            //alertDialog.setMessage(tmpStr10);
            //alertDialog.show();


        }
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);

    }
}
