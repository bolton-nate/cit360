package com.natebolton.timeoffrequest;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SubmitOwnRequest extends AppCompatActivity {

    EditText startDate, returnDate, daysRequested, reasonText;
    Button submitRequestSubmit;
    TextView submitTitle;

    public static final String DESTURL = "http://10.0.2.2:8080/TimeOffRequest/SubmitRequestControl";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_own_request);
        sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        findViewsById();

        submitRequestSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // execute method invokes doInBackground() where we open a Http URL connection using the given Servlet URL
                //and get output response from InputStream and return it.
                new SubmitRequest().execute();

            }
        });
    }

    private void findViewsById() {

        startDate = (EditText) findViewById(R.id.startDate);
        returnDate = (EditText) findViewById(R.id.returnDate);
        daysRequested = (EditText) findViewById(R.id.daysRequested);
        reasonText = (EditText) findViewById(R.id.reasonText);
        submitRequestSubmit = (Button) findViewById(R.id.submitRequestSubmit);
        submitTitle = (TextView) findViewById(R.id.submitTitle);
    }

    private class SubmitRequest extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... args) {
            String startDateStr = startDate.getText().toString();
            String returnDateStr = returnDate.getText().toString();
            int daysRequestedInt = 0;
            try {
                daysRequestedInt = Integer.parseInt(daysRequested.getText().toString());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
            String reasonTextStr = reasonText.getText().toString();
            Integer emp_id = sharedPreferences.getInt(MainActivity.uid,0);
            Date startDate = null;
            Date endDate = null;

            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            try {
                startDate = df.parse(startDateStr);
                endDate = df.parse(returnDateStr);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            try {
                Log.d("SUBMIT INPUT", startDate.toString() + " and " + endDate.toString());
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
            if (startDate != null && endDate != null && daysRequestedInt != 0) {
                Requests request = new Requests(emp_id, startDate, endDate, reasonTextStr, daysRequestedInt);
                StringBuilder result = new StringBuilder();
                try {
                    Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(request);
                    //Log.d("RESPONSE",json);
                    JSONObject jsonObj = new JSONObject(json);
                    //Log.d("RESPONSE",jsonObj.toString());

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
                        InputStream stream = httpConnection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                        String line;

                        while ((line = reader.readLine()) != null) {
                            Log.d("RESPONSE",line);
                            result.append(line);
                        }
                    }
                    ((HttpURLConnection) connection).disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                Log.d("SUBMIT INPUT END", result.toString() + "That was \"result\"");
                return result.toString();
            }
            Log.d("SUBMIT INPUT END", "returning null... :(");
            return null;
        }

        protected void onPostExecute(String result) {
            submitTitle.setTextSize(12);
            if (!result.equals("")) {
                Log.d("SUBMIT INPUT POST", result);
                JSONParser parser = new JSONParser();
                org.json.simple.JSONObject jsonObj = null;
                try {
                    jsonObj = (org.json.simple.JSONObject) parser.parse(result);
                } catch (org.json.simple.parser.ParseException ex ) {
                    ex.printStackTrace();
                }
                //Log.d("JSONOBJ",jsonObj.toString());
                //Log.d("JSONOBJ_emp_id",jsonObj.get("emp_id").toString());
                String response = null;
                try {
                    response = (String) jsonObj.get("response");
                    if (response.equals("Request Submitted Successfully")) {
                        submitTitle.setTextColor(Color.GREEN);
                    } else {
                        submitTitle.setTextColor(Color.RED);
                    }
                    submitTitle.setText(response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Log.d("SUBMIT INPUT POST", "PROBLEM MESSAGE!");
                submitTitle.setTextColor(Color.RED);
                submitTitle.setText("Please Check Your Input\nDates and Total Days off can not be empty or 0.");
            }

        }
    }

}
