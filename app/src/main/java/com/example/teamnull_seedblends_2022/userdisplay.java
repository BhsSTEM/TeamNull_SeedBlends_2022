package com.example.teamnull_seedblends_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class userdisplay extends AppCompatActivity {
    private TextView textViewWelcome, textViewFullName, textViewFullEmail;
    private Button logout;
    private ProgressBar progressBar;
    private String name, email;
    private FirebaseAuth authProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdisplay);

        logout = (Button) findViewById(R.id.logoutButton);

      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              FirebaseAuth.getInstance().signOut();
              startActivity(new Intent(userdisplay.this, Login.class));
              // This will logout the user
          }
      });


        getSupportActionBar().setTitle("Home");

        textViewWelcome = findViewById(R.id.textview_welcome);
        textViewFullName = findViewById(R.id.textview_full_name);
        textViewFullEmail = findViewById(R.id.textview_full_email);
        logout = findViewById(R.id.logoutButton);
        progressBar = findViewById(R.id.textview_progressbar);


        authProfile = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = authProfile.getCurrentUser();


        if(firebaseUser == null){
            Toast.makeText(this, "Something went wrong! Pleaes check back later!", Toast.LENGTH_LONG).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            showUserProfile(firebaseUser);
        }

    }

    private void showUserProfile(FirebaseUser firebaseUser) {
        String userID = firebaseUser.getUid();
// Extracting user data base
        name = firebaseUser.getDisplayName();
        email = firebaseUser.getEmail();
        userID = authProfile.getUid();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/Users/" + userID + "/name/");
        textViewFullEmail.setText(email);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post = dataSnapshot.getValue(String.class);
                name = post;
                textViewFullName.setText(name);
                textViewWelcome.setText("Hello! " + name + "!");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}