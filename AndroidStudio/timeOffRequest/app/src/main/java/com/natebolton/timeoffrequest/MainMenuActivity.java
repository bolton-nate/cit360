package com.natebolton.timeoffrequest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    Button logout, viewRequests, submitRequest;
    TextView welcomeMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        findViewsById();

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainMenuActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        viewRequests.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("View Requests Coming Soon!");

            }
        });

        submitRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("Submit Requests Coming Soon!");

            }
        });

        //Log.d("GETSTRING", sharedPreferences.getString(MainActivity.firstname,""));
        welcomeMessage.setText("Welcome, " + sharedPreferences.getString(MainActivity.firstname,"") + " " + sharedPreferences.getString(MainActivity.lastname,""));
    }

    private void findViewsById() {

        logout = (Button) findViewById(R.id.buttonLogout);
        viewRequests = (Button) findViewById(R.id.viewRequests);
        submitRequest = (Button) findViewById(R.id.submitRequest);
        welcomeMessage = (TextView) findViewById(R.id.textView4);


    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main_menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }*/

/*    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.viewRequests:
                //your action
                break;
            case R.id.submitRequest:
                //your action
                break;
            case R.id.logoff:
                Intent i = new Intent(MainMenuActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }*/
}
