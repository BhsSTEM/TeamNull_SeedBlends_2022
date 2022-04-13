package com.example.teamnull_seedblends_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView register;
    private EditText signInEmail, signInPassword;
    private Button logInButton;
    private ProgressBar signInProgressBar;
    // This is got the google fire base login System
    private FirebaseAuth mAuth;
    //This is got the google fire base login System



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
            register.setOnClickListener(this);
// DONT TOUCH THIS

        logInButton = (Button) findViewById(R.id.logInButton);
            logInButton.setOnClickListener(this);
                    // This is for the login button

        signInEmail = (EditText) findViewById(R.id.signInEmail);
            signInPassword = (EditText) findViewById(R.id.signInPassword);
                    // This is for the Password and Email
        signInProgressBar = (ProgressBar) findViewById(R.id.signInProgressBar);

            mAuth = FirebaseAuth.getInstance();
            //FIRE BASE AUTH DO NOT TOUCH
    }

    public void launchMainScreen(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:
                startActivity(new Intent(this, signUpScreen.class));
                break;

            case R.id.logInButton:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        // This is our path method
        String email = signInEmail.getText().toString().trim();
        String password = signInPassword.getText().toString().trim();
        // Variables for the values

        if(email.isEmpty()){
            signInEmail.setError("Please enter your email");
            signInEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            signInEmail.setError("Please enter a valid Email!");
            signInEmail.requestFocus();
            return;
        }

        if(password.isEmpty() || password.length() < 6){
            signInPassword.setError("Please enter your passsword!");
            signInPassword.requestFocus();
            return;
        }

        signInProgressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // This is where we Redirect to user profile
                    startActivity(new Intent(Login.this, MainActivity.class));

                }else{
                    Toast.makeText(Login.this, "Failed to login! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}