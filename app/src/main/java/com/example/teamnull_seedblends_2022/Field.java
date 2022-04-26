package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Field extends AppCompatActivity {

    ListView listView;
    EditText field_name;
    EditText field_coords;
    Button button;
    ArrayList<FieldItem> list = new ArrayList<>();
    ArrayAdapter<FieldItem> adapter;
    String inputText;
    String inputCoords;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);

        button = findViewById(R.id.new_field);
        listView = findViewById(R.id.list);
        field_name = findViewById(R.id.enter_field_name);
        field_coords = findViewById(R.id.enter_coordinates);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText = field_name.getText().toString();
                inputCoords = field_coords.getText().toString();
                FieldItem item = new FieldItem(inputText, inputCoords);
                list.add(item);
                field_name.setText("");
                field_coords.setText("");
                adapter.notifyDataSetChanged();
            }
        };

        Intent intent = new Intent(this, FieldInfo.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent.putExtra("field_name", inputText);
                intent.putExtra("field_coords", inputCoords);
                startActivity(intent);
            }
        });

        button.setOnClickListener(onClickListener);
        listView.setAdapter(adapter);


    }

    public void launchFieldInfoPage(View view) {
        Intent intent = new Intent(this, FieldInfo.class);
        intent.putExtra("id", view.getId());
        startActivity(intent);
    }

    public void launchTrackingPage(MenuItem item) {
        Intent intent = new Intent(this, Tracking.class);
        startActivity(intent);
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