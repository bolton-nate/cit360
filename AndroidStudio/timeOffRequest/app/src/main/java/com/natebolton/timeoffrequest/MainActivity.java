package com.natebolton.timeoffrequest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity {

    EditText uname, password;
    Button submit;
    TextView outputText;

//    public static final String DESTURL = "http://192.168.1.41:8080/TimeOffRequest/logincontrol";
    public static final String DESTURL = "http://10.0.2.2:8080/TimeOffRequest/logincontrol";

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String uid = "uidKey";
    public static final String usertitle = "titleKey";
    public static final String firstname = "fnameKey";
    public static final String lastname = "lnameKey";
    SharedPreferences sharedpreferences;
    //SharedPreferences.Editor editor;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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
            EmployeeAuth empAuth = null;
            StringBuilder result = new StringBuilder();
            if (username != null && pass != null) {

                empAuth = new EmployeeAuth(null,username,pass);
                try {
                    Gson gson = new GsonBuilder().create();
                    String json = gson.toJson(empAuth);// obj is your object
                    //Log.d("RESPONSE",json);
                    JSONObject jsonObj = new JSONObject(json);
                    //Log.d("RESPONSE",jsonObj.toString());

                    URL url = new URL(DESTURL);
                    URLConnection connection = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection) connection;
                    httpConnection.setRequestMethod("POST");
                    //String urlParameters = "uname=" + username + "&pw=" + pass;
                    //httpConnection.connect();
                    //httpConnection.setDoOutput(true);
                    //Create JSONObject here

                    //                jsonObj.put("uname", username);
                    //                jsonObj.put("pw", pass);
                    try (DataOutputStream wr = new DataOutputStream(httpConnection.getOutputStream())) {
                        //wr.writeBytes(urlParameters);
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
            }
            return result.toString();

        }

        protected void onPostExecute(String result) {
            JSONParser parser = new JSONParser();
            org.json.simple.JSONObject jsonObj = null;
            try {
                jsonObj = (org.json.simple.JSONObject) parser.parse(result);
            } catch (ParseException ex ) {
                ex.printStackTrace();
            }
            //Log.d("JSONOBJ",jsonObj.toString());
            //Log.d("JSONOBJ_emp_id",jsonObj.get("emp_id").toString());
            String response = null;
            try {
                response = (String) jsonObj.get("response");
                if (response.equals("Logged In!")) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();

                    editor.putInt(uid, ((Long) jsonObj.get("emp_id")).intValue());
                    editor.putString(usertitle, (String) jsonObj.get("employeeTitle"));
                    editor.putString(firstname, (String) jsonObj.get("firstname"));
                    editor.putString(lastname, (String) jsonObj.get("lastname"));
                    editor.apply();

/*                    if (jsonObj.get("employeeTitle").toString().equals("admin")) {
                        Log.d("BRANCH: ", "starting admin menu");
                        startActivity(new Intent(MainActivity.this, MainMenuAdminActivity.class));
                    } else {*/
                    Log.d("BRANCH: ", "starting user menu");
                    startActivity(new Intent(MainActivity.this, MainMenuActivity.class));
                    //}
                } else {
                    outputText.setText(response);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                outputText.setText("Unable to connect to server.  Please try again later.");
            }
        }

    }
}