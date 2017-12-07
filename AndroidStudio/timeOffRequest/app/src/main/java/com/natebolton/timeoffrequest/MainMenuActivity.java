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

    Button logout, viewRequests, submitRequest, adminMenu;
    TextView welcomeMessage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        findViewsById();

        if (sharedPreferences.getString(MainActivity.usertitle,"").equals("admin")) {
            adminMenu.setVisibility(View.VISIBLE);
            adminMenu.setTextColor(Color.RED);
        } else {
            adminMenu.setVisibility(View.GONE);
        }

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
                Log.d("MAINMENU: ", "starting view requests");
                startActivity(new Intent(MainMenuActivity.this, ViewOwnRequests.class));

            }
        });

        submitRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
/*                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("Submit Requests Coming Soon!");*/
                Log.d("MAINMENU: ", "starting submit own request");
                startActivity(new Intent(MainMenuActivity.this, SubmitOwnRequest.class));
            }
        });

        adminMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.d("BRANCH: ", "starting admin menu");
                startActivity(new Intent(MainMenuActivity.this, MainMenuAdminActivity.class));

            }
        });

        //Log.d("GETSTRING", sharedPreferences.getString(MainActivity.firstname,""));
        welcomeMessage.setText("User:  " + sharedPreferences.getString(MainActivity.firstname,"") + " " + sharedPreferences.getString(MainActivity.lastname,""));
    }

    private void findViewsById() {

        logout = (Button) findViewById(R.id.buttonLogout);
        viewRequests = (Button) findViewById(R.id.viewRequests);
        submitRequest = (Button) findViewById(R.id.submitRequestSubmit);
        adminMenu = (Button) findViewById(R.id.adminMenu);
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
