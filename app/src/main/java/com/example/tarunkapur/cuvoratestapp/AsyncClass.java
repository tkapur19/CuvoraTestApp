package com.example.tarunkapur.cuvoratestapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// AsyncTaskLoader Class for handling networking and extracting response from API

public class AsyncClass extends AsyncTaskLoader<List<ModalClass>> {

    List<ModalClass> data=new ArrayList<>();
    String mUrl;

    public AsyncClass(Context context,  String mUrl) {
        super(context);
        this.mUrl = mUrl;
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    public List<ModalClass> loadInBackground() {

        Log.i("myMessage", "loadInBackground: ");

        // Initialing URL for Http connection

        URL url=null;
        JSONObject jsonObject=null;
        String response=null;

        try {

            url=new URL(mUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {


            Log.i("myMessage", String.valueOf(url));

            // Making a HttpRequest and opening a connection
            HttpURLConnection urlConnection= (HttpURLConnection) url.openConnection()  ;
            InputStream inputStream=urlConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            response =bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();


            } catch (IOException e) {
            e.printStackTrace();
        }

       try {

            // Handling Json response Recieved from API calling

           jsonObject = new JSONObject(response);

           // Obtaining Json Array from the "list " array in the response containing all the info of trending Celebs cars
           JSONArray jsonArray=jsonObject.getJSONObject("data").getJSONArray("list");
           Log.i("myMessage", String.valueOf(jsonArray.length()));

           // Extracting information for each object of the "list" array in the response.
           for(int i=0;i<jsonArray.length();i++){
               Log.i("myMessage", String.valueOf(jsonArray.length()));

               JSONObject object=jsonArray.getJSONObject(i);
               ModalClass modalClassobject=new ModalClass();

               modalClassobject.setShareText(object.getJSONArray("vehicleDetails").getJSONObject(0).getString("shareText"));

               modalClassobject.setImageURL(object.getString("imageURL"));

               modalClassobject.setOwnerName(object.getJSONArray("vehicleDetails").getJSONObject(0).getString("displayName"));

            // Extracting arrayList of "info" for each object in the "list" array of the response
            List<info> infos= new ArrayList<>();
               for (int j=0;j<object.getJSONArray("vehicleDetails").getJSONObject(0).getJSONArray("info").length();j++){
                  info info=new info();
                  info.setKey(object.getJSONArray("vehicleDetails").getJSONObject(0).getJSONArray("info").getJSONObject(j).getString("key"));
                  info.setValue(object.getJSONArray("vehicleDetails").getJSONObject(0).getJSONArray("info").getJSONObject(j).getString("value"));
                  infos.add(info);

               }
               modalClassobject.setInfoList(infos);


               // Adding  final object of "list" containing required information of the object from the "list" array in the response.
               data.add(modalClassobject);









           }
        }catch (JSONException e) {
           e.printStackTrace();
       }

// Returning final List of objects obtained from the "list" array of the response
return data;


    }
}
