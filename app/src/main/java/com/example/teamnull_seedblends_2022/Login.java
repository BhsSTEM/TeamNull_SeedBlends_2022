package com.example.teamnull_seedblends_2022;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class Login extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }



    public void launchMainScreen(View view){
    Intent intent = new Intent(this, MainActivity.class);
    startActivity(intent);
}

public void launchSignUpScreen(View view){
        Intent intent = new Intent(this, signUpScreen.class);
        startActivity(intent);
}






}