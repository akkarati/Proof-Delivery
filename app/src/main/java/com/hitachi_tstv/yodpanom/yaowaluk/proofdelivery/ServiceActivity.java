package com.hitachi_tstv.yodpanom.yaowaluk.proofdelivery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView nameDriverTextview,idDriverTextView;
    private Button jobListButton , closeButton;
    private ListView listView;
    private String[] loginStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bing Widget
        nameDriverTextview = (TextView) findViewById(R.id.textView2);
        idDriverTextView = (TextView) findViewById(R.id.textView4);
        jobListButton = (Button) findViewById(R.id.button4);
        closeButton = (Button) findViewById(R.id.button2);
        listView = (ListView) findViewById(R.id.listJob);

        //Get Value from Intent
        loginStrings = getIntent().getStringArrayExtra("Login");

        //Show Name
        nameDriverTextview.setText(loginStrings[1]);



    } // Main Method

} // Main Class
