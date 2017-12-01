package com.natebolton.timeoffrequest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URL;
import java.net.URLConnection;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText uname, password;
    Button submit;
    TextView outputText;

    public static final String DESTURL =
            "http://10.0.2.2:8080/TimeOffRequest/logincontrol";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewsById();

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // execute method invokes doInBackground() where we open a Http URL connection using the given Servlet URL
                //and get output response from InputStream and return it.
                new Login().execute();

            }
        });
    }

    private void findViewsById() {

        uname = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPass);
        submit = (Button) findViewById(R.id.button);
        outputText = (TextView) findViewById(R.id.textOutput);
    }

    private class Login extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... args) {
            // Getting username and password from user input
            String username = uname.getText().toString();
            String pass = password.getText().toString();

/*            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("u",username));
            params.add(new BasicNameValuePair("p",pass));
            json = jParser.makeHttpRequest(url_login, "GET", params);
            String s=null;*/

            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(DESTURL);
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("POST");
                String urlParameters = "uname=" + username + "&pw=" + pass;
                //httpConnection.connect();
                //httpConnection.setDoOutput(true);
                try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
                    wr.writeBytes(urlParameters);
                    wr.flush();
                }
                if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream stream = httpConnection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                    String line;

                    while ((line = reader.readLine()) != null) {
                        Log.d("RESPONSE",line);
                        result.append(line);
                    }

                    //outputText.setText(stream.toString());
                }
                ((HttpURLConnection) connection).disconnect();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return result.toString();

        }

        protected void onPostExecute(String result) {
            outputText.setText(result);
        }

    }
}