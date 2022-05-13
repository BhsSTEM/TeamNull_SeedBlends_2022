package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity
{
// This is for the certain classes in the XML code

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo_foreground);

        setContentView(R.layout.activity_main);


    }

    public void launchFieldPage(View view) {
        Intent intent = new Intent(this, Field.class);
        startActivity(intent);
    }

    public void launchFieldPage(MenuItem item) {
        Intent intent = new Intent(this, Field.class);
        startActivity(intent);
    }

    public void launchDocumentationPage(View view) {
        Intent intent = new Intent(this, Documentation.class);
        startActivity(intent);
    }

    public void launchDocumentationPage(MenuItem item) {
        Intent intent = new Intent(this, Documentation.class);
        startActivity(intent);
    }
    public void launchUserProfile(View view) {
        Intent intent = new Intent(MainActivity.this, userdisplay.class);
        startActivity(intent);
        // This will launch the user profile
    }
}