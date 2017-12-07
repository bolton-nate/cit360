package com.natebolton.timeoffrequest;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.List;

import static com.natebolton.timeoffrequest.MainActivity.DESTURL;

public class ViewOwnRequests extends AppCompatActivity {

    TextView textViewTitle;
    ListView requestList;

    public static final String DESTURL = "http://10.0.2.2:8080/TimeOffRequest/RequestControl";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_own_requests);
        sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        findViewsById();

        new ViewRequests().execute();
    }

    private void findViewsById() {
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        requestList = (ListView) findViewById(R.id.requestList);
    }

    private class ViewRequests extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... args) {
            StringBuilder result = new StringBuilder();
            try {
                JSONObject jsonObj = new JSONObject();
                try {
                    jsonObj.put("emp_id", sharedPreferences.getInt(MainActivity.uid,0));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.d("VIEWREQUESTS_JSON",jsonObj.toString());

                URL url = new URL(DESTURL);
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("POST");
                try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
                    wr.writeBytes(URLEncoder.encode(jsonObj.toString(),"UTF-8"));
                    wr.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.d("VIEWREQUESTS","Getting Response!");
                    InputStream stream = httpConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        Log.d("VIEWREQUESTS",line);
                        result.append(line);
                    }
                }
                ((HttpURLConnection) connection).disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return result.toString();
        }

        protected void onPostExecute(String result) {
            //textViewTitle.setText(result.toString());
            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject jsonObj = null;

            try {
                jsonObj = (org.json.simple.JSONObject) parser.parse(result);
            } catch (ParseException ex ) {
                ex.printStackTrace();
            }
            try {
                Log.d("JSONOBJ", jsonObj.toString());
                //Log.d("JSONOBJ_emp_id",jsonObj.get("emp_id").toString());
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Requests>>() {}.getType();
            List<Requests> myList = (List<Requests>) gson.fromJson(jsonObj.get("requests").toString(), listType);
            String[] listItems = new String[myList.size()];
            int i = 0;
            for (Requests request : myList) {
                //Log.d("REQUEST LIST ID", request.getRequestId().toString());
                listItems[i] = "Request Submit Date: " + DateFormat.getDateInstance().format(request.getTimestamp())
                        + "\nTime Off Start Date: " + DateFormat.getDateInstance().format(request.getStartDate())
                        + "\nReturn to Work Date: " + DateFormat.getDateInstance().format(request.getReturnDate())
                        + "\nReason: " + request.getReason()
                        + "\nDays Requested:  " + request.getDaysRequested();
                i++;
            }

            //Log.d("lISTITEMS", listItems);
            ArrayAdapter adapter = new ArrayAdapter(ViewOwnRequests.this, android.R.layout.simple_list_item_1, listItems);
            Log.d("ARRAYADAPTER", adapter.toString());
            requestList.setAdapter(adapter);
            //List list = (List) jsonObj.get("requests");
            //Log.d("NOW A LIST", requestList.toString());
        }

    }
}
