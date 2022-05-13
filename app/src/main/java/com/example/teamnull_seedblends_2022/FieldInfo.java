package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class FieldInfo extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView title;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo_foreground);

        setContentView(R.layout.activity_field_info);

        String name = getIntent().getExtras().getString("field_name");
        title = findViewById(R.id.field_title);
        title.setText(name);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList<String> coords = new ArrayList<String>(Arrays.asList(getIntent().getExtras().getString("field_coords").split(",")));

        LatLngBounds bounds = new LatLngBounds(
                new LatLng(Double.parseDouble(coords.get(0)),Double.parseDouble(coords.get(2))),
                new LatLng(Double.parseDouble(coords.get(1)),Double.parseDouble(coords.get(3)))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 0));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 5000, null);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void launchFieldPage(MenuItem item) {
        Intent intent = new Intent(this, Field.class);
        startActivity(intent);
    }

    public void launchDocumentationPage(MenuItem item) {
        Intent intent = new Intent(this, Documentation.class);
        startActivity(intent);
    }


}