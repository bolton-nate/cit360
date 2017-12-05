package com.natebolton.timeoffrequest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuAdminActivity extends AppCompatActivity {

    Button logout, viewEmployeeRequests, viewAdminReport, viewEmployees, createEmployee;
    TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_admin);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        findViewsById();

        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(MainMenuAdminActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);

            }
        });

        viewEmployeeRequests.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("View Employee Requests Coming Soon!");

            }
        });

        viewAdminReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("View Admin Report Coming Soon!");

            }
        });

        viewEmployees.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("View Employees Coming Soon!");

            }
        });

        createEmployee.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                welcomeMessage.setTextColor(Color.RED);
                welcomeMessage.setTextSize(22);
                welcomeMessage.setText("Create Employee Coming Soon!");

            }
        });

        welcomeMessage.setText("Welcome, " + sharedPreferences.getString(MainActivity.firstname,"") + " " + sharedPreferences.getString(MainActivity.lastname,""));
    }

    private void findViewsById() {

        logout = (Button) findViewById(R.id.buttonLogout);
        viewEmployeeRequests = (Button) findViewById(R.id.viewEmployeeRequests);
        viewAdminReport = (Button) findViewById(R.id.viewAdminReport);
        viewEmployees = (Button) findViewById(R.id.viewEmployees);
        createEmployee = (Button) findViewById(R.id.createEmployee);
        welcomeMessage = (TextView) findViewById(R.id.textView4);
    }
}
