package com.example.consultation.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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
import java.util.List;

public class pharmacy extends Activity implements AdapterView.OnItemClickListener{

    ListView lv;
    ArrayAdapter<String> adapter;
    String address2, message, success;
    URLs urLs;

    String address = urLs.BASE_URL+"include/storelist.php?";

    InputStream is = null;
    String line = null;
    String result = null;
    //    String[] storeNames;
    Context context;
    TextView resultText;
    Button buttonSave;
    private List<String> stores;
    private Spinner sp_parmacy;
    EditText medicines;

    List<List<String>> pharmacyNameList;
    List<String> pharmacyId;
    String pharmacyName = null;

    ParserJSON parserJSON;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy);

        context = this;

        resultText = (TextView) findViewById(R.id.editText);
        buttonSave = (Button) findViewById(R.id.button);
        stores = new ArrayList<>();
        sp_parmacy = (Spinner) findViewById(R.id.spinner);
        medicines = (EditText) findViewById(R.id.editText);

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        parserJSON = new ParserJSON(address);
        pharmacyNameList = parserJSON.parsePharmacy();
        pharmacyId = pharmacyNameList.get(0);
        //      ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pharmacyNameList.get(0));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pharmacyNameList.get(1));
        sp_parmacy.setAdapter(adapter);
//        sp_parmacy.setAdapter(adapter2);

        //final      String getpos = pharmacyId.get(sp_parmacy.getSelectedItemPosition()).toString();


        //      Toast.makeText(context, getpos+"", Toast.LENGTH_LONG).show();


        //StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder().permitNetwork().build()));

        SharedPreferences pref = getApplicationContext().getSharedPreferences("Myid", 0);
        int Patientid = pref.getInt("PatientId", 0);
        final String patid = String.valueOf(Patientid);


        int Docid = pref.getInt("DocId", 0);
        final String docid = String.valueOf(Docid);

        final String asdf = pharmacyId.toString();

        buttonSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String medicines = resultText.getText().toString().replaceAll(" ", "%20");
                String patientid = patid.toString();
                String doctorid = docid.toString();
                //String medicine = medicines.getText().toString();
                String pharmacyName = sp_parmacy.getSelectedItem().toString();
                String getpos = String.valueOf(sp_parmacy.getSelectedItemPosition());//pharmacyId.get(sp_parmacy.getSelectedItemPosition()).toString();
                int p = Integer.parseInt(getpos);
                p++;
                getpos = Integer.toString(p);

                //             Toast.makeText(context, getpos, Toast.LENGTH_LONG).show();

                //address2 = "http://ocs.ebs-agro-portal.com/include/speech.php?Medicines= + medicines +&PatientId=+patid+&DoctorId=+docid";
                address2 = urLs.BASE_URL+"include/stt.php?DoctorId=1"
                        +"&PatientId="+ patientid
                        + "&PharmacyId="+ getpos
                        + "&Medicines="+ medicines;
                getData();
                Toast.makeText(context, getpos, Toast.LENGTH_LONG).show();
                Toast.makeText(context, "Successfully inserted", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void getData()
    {
        try {
            URL url = new URL(address2);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            is = new BufferedInputStream(con.getInputStream());
            Log.i("tagconvertstr", "["+result+"]");
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
            Log.d("error", "isme arha hy");
            Log.d("url", address2);
            Log.d("is data", is.toString());


            e.printStackTrace();
        }

        try {
            JSONArray js = new JSONArray(result);
            JSONObject jo = null;
            Log.d("error", "isme arha hy");


            for (int i = 0; i < js.length(); i++) {
                jo = js.getJSONObject(i);
                success = jo.getString("success");
                message = jo.getString("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        pharmacyName = pharmacyId.get(position);


    }
}
