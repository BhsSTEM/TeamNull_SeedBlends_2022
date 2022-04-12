package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity
{

// This is for the certain classes in the XML code

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchTrackingPage(View view) {
        Intent intent = new Intent(this, Tracking.class);
        startActivity(intent);
    }

    public void launchFieldPage(View view) {
        Intent intent = new Intent(this, Field.class);
        startActivity(intent);
    }

    public void launchDocumentationPage(View view) {
        Intent intent = new Intent(this, Documentation.class);
        startActivity(intent);
    }

}