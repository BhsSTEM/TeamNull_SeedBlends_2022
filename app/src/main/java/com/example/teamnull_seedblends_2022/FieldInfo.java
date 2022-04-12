package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLngBounds latlongbounds;

        latlongbounds = new LatLngBounds(
                new LatLng(Double.parseDouble(coords.get(0)),Double.parseDouble(coords.get(2))),
                new LatLng(Double.parseDouble(coords.get(1)),Double.parseDouble(coords.get(3)))
        );

        mMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latlongbounds, 0));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 5000, null);
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }


}