package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;

public class Documentation extends AppCompatActivity {

    private ArrayList<DocumentationCard> mDocumentationList;

    private RecyclerView mRecyclerView;
    private DocumentationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private EditText associatedFieldEditText;
    private EditText dateEditText;
    private EditText documentationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo_foreground);

        setContentView(R.layout.activity_documentation);

        createDocumentationList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.insert_documentation);
        associatedFieldEditText = findViewById(R.id.associatedFieldEditText);
        dateEditText = findViewById(R.id.dateEditText);
        documentationEditText = findViewById(R.id.documentationEditText);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem(associatedFieldEditText.getText().toString(), dateEditText.getText().toString(), documentationEditText.getText().toString());

                associatedFieldEditText.setText("");
                dateEditText.setText("");
                documentationEditText.setText("");
            }
        });
    }

    public void insertItem(String af, String dt, String doc) {
        mDocumentationList.add(new DocumentationCard(af, dt, doc));
        mAdapter.notifyItemInserted(mDocumentationList.size()-1);
    }

    public void createDocumentationList() {
        mDocumentationList = new ArrayList<>();
//        mDocumentationList.add(new DocumentationCard("", "Line 1", "Line 2"));
//        mDocumentationList.add(new DocumentationCard("", "Line 3", "Line 4"));
//        mDocumentationList.add(new DocumentationCard("", "Line 5", "Line 6"));
    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DocumentationAdapter(mDocumentationList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);
        return true;
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

    public void filterList(View view) {
        String filterPattern = ((EditText) findViewById(R.id.search_documentation)).getText().toString().toLowerCase().trim();
        mAdapter.getFilter().filter(filterPattern);
    }
}