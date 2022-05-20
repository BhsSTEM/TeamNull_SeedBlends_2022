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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
        setTheme(R.style.DocuSeed);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_logo_foreground);

        setContentView(R.layout.activity_documentation);

        createDocumentationList();
        buildRecyclerView();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setTooltipText("Put Application Process, Seed Mix, Seeding Rate, Crop Termination Date, Termination Method");

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
        mDocumentationList.add(new DocumentationCard("East Field", "5/19/22", "Application Process: Spreader\nSeed Mix: 20% corn, 80% rice\nSeeding Rate: 40 lbs/acre\nCrop Termination Date: 5/18/22\nTermination Method: Chemicals"));
        mDocumentationList.add(new DocumentationCard("North Field", "5/18/22", "Application Process: Airseeder\nSeed Mix: 50% soybeans, 50% potatoes\nSeeding Rate: 60 lbs/acre\nCrop Termination Date: 5/17/22\nTermination Method: Crimping"));
        mDocumentationList.add(new DocumentationCard("South Field", "5/17/22", "Application Process: Airseeder\nSeed Mix: 70% corn, 30% tree nuts\nSeeding Rate: 30 lbs/acre\nCrop Termination Date: 5/15/22\nTermination Method: Chemicals"));
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