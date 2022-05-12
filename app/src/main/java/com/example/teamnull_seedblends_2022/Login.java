package com.example.teamnull_seedblends_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
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
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView register, forgotPassword;
    private EditText signInEmail, signInPassword;
    private Button logInButton;
    private ProgressBar signInProgressBar;
    boolean passwordVisible;
    // This is got the google fire base login System
    private FirebaseAuth mAuth;
    //This is got the google fire base login System



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
            register.setOnClickListener(this);


        logInButton = (Button) findViewById(R.id.logInButton);
            logInButton.setOnClickListener(this);
                    // This is for the login button

        signInEmail = (EditText) findViewById(R.id.signInEmail);
            signInPassword = (EditText) findViewById(R.id.signInPassword);
            signInPassword.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    final int Right=2;
                    if(event.getAction() == MotionEvent.ACTION_UP){
                        if(event.getRawX() >= signInPassword.getRight()-signInPassword.getCompoundDrawables()[Right].getBounds().width()){
                            int selection =signInPassword.getSelectionEnd();
                            if(passwordVisible){
                                //set drawable image here
                                signInPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_off_24, 0 );
                                //hide
                                signInPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                                passwordVisible = false;
                            }else{
                                signInPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_visibility_24, 0 );
                                //hide
                                signInPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                                passwordVisible = true;
                            }
                            signInPassword.setSelection(selection);
                            return true;
                        }
                    }
                    return false;
                }
            });

                    // This is for the Password and Email
        signInProgressBar = (ProgressBar) findViewById(R.id.signInProgressBar);

            mAuth = FirebaseAuth.getInstance();
            //FIRE BASE AUTH DO NOT TOUCH
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(this);

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

            case R.id.forgotPassword:
            startActivity(new Intent(this, ForgotPassword.class));
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
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()){
                        startActivity(new Intent(Login.this, MainActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(Login.this, "Check email! Then log back in!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Login.this, "Failed to login! Please try again!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openRegisterPage(View view) {
        Intent intent = new Intent(this, signUpScreen.class);
        startActivity(intent);
    }

    public void openForgotPasswordPage(View view) {
        Intent intent = new Intent(this, ForgotPassword.class);
        startActivity(intent);
    }
}
