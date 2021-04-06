package com.example.consultation.myapplication;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class ParserJSON {

    String uri;
    HttpClient httpclient;
    private static final String ENC = "UTF-8";
    public static boolean DEBUG = true;


    public ParserJSON(String uri){
        this.uri = uri;
    }

    public String getData() throws IOException {

        httpclient = new DefaultHttpClient();
        String data = "";
        HttpGet httpget = new HttpGet(uri);
        // output the response content.
        System.out.println("Token and Token Secrect:");

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            InputStream instream = entity.getContent();
            int len;
            byte[] tmp = new byte[2048];
            while ((len = instream.read(tmp)) != -1) {
//                System.out.println(new String(tmp, 0, len, ENC));
                data += new String(tmp, 0, len, ENC);
            }
        }
        return data;
    }

    public List<List<String>> parsePharmacy(){

        //getJson data form url
        String jsonDataStream = null;
        try {
            jsonDataStream = getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("DATA: " + jsonDataStream);

        List<List<String>> pharmacy = new ArrayList<>();
        List<String> id = new ArrayList<>();
        List<String> name = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        JSONArray object = null;
        try {
            object = (JSONArray) parser.parse(jsonDataStream);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            JSONObject innerObject = (JSONObject) iterator.next();
            id.add((String) innerObject.get("PharmacyId"));
            name.add((String) innerObject.get("Name"));
        }
        pharmacy.add(id);
        pharmacy.add(name);
        return pharmacy;
    }//end of parseProductCategories



    public List<List<String>> parseDoctors(){

        //getJson data form url
        String jsonDataStream = null;
        try {
            jsonDataStream = getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("DATA: " + jsonDataStream);

        List<List<String>> doctors = new ArrayList<>();
        List<String> id = new ArrayList<>();
        List<String> name = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        JSONArray object = null;
        try {
            object = (JSONArray) parser.parse(jsonDataStream);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            JSONObject innerObject = (JSONObject) iterator.next();
            id.add((String) innerObject.get("DocId"));
            name.add((String) innerObject.get("Name"));
        }
        doctors.add(id);
        doctors.add(name);
        return doctors;
    }//end of parseProductCategories



    public List<List<String>> parserequests(){

        //getJson data form url
        String jsonDataStream = null;
        try {
            jsonDataStream = getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("DATA: " + jsonDataStream);

        List<List<String>> doctors = new ArrayList<>();
        List<String> id = new ArrayList<>();
        List<String> name = new ArrayList<String>();
        JSONParser parser = new JSONParser();
        JSONArray object = null;
        try {
            object = (JSONArray) parser.parse(jsonDataStream);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            JSONObject innerObject = (JSONObject) iterator.next();
            id.add((String) innerObject.get("users_id"));
            name.add((String) innerObject.get("message"));
        }
        doctors.add(id);
        doctors.add(name);
        return doctors;
    }//end of parseProductCategories



    public List<List<String>> parseUprofile(){

        //getJson data form url
        String jsonDataStream = null;
        try {
            jsonDataStream = getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("DATA: " + jsonDataStream);

        List<List<String>> pharmacy = new ArrayList<>();
        List<String> username = new ArrayList<>();
        List<String> email = new ArrayList<String>();
        List<String> age = new ArrayList<>();
        List<String> gender = new ArrayList<String>();
        List<String> contact = new ArrayList<>();
        List<String> nic = new ArrayList<String>();
        List<String> quanlification = new ArrayList<>();
        List<String> pass = new ArrayList<>();
        List<String> mob = new ArrayList<>();
        List<String> status = new ArrayList<>();
        List<String> address = new ArrayList<>();

        // List<String> name = new ArrayList<String>();


        JSONParser parser = new JSONParser();
        JSONArray object = null;
        try {
            object = (JSONArray) parser.parse(jsonDataStream);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterator iterator = object.iterator();
        while (iterator.hasNext()){
            JSONObject innerObject = (JSONObject) iterator.next();

            username.add((String) innerObject.get("Name"));
            age.add((String) innerObject.get("Age"));
            gender.add((String) innerObject.get("Gender"));
            quanlification.add((String) innerObject.get("Qualification"));
            nic.add((String) innerObject.get("Cnic"));
            email.add((String) innerObject.get("Email"));
            pass.add((String) innerObject.get("Password"));
            mob.add((String) innerObject.get("Mobile"));
            status.add((String) innerObject.get("Status"));
            address.add((String) innerObject.get("Address"));


        }
        pharmacy.add(username);
        pharmacy.add(age);
        pharmacy.add(gender);
        pharmacy.add(quanlification);
        pharmacy.add(nic);
        pharmacy.add(email);
        pharmacy.add(pass);
        pharmacy.add(mob);
        pharmacy.add(status);
        pharmacy.add(address);
        return pharmacy;
    }//end of parseProductCategories


}
