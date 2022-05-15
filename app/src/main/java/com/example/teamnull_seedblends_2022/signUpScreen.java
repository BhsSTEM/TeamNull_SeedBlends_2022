package com.example.teamnull_seedblends_2022;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class signUpScreen extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView banner, goToLoginPage;
    private EditText registerName, registerPassword, registerEmail;
    private Button registerButton;
    private ProgressBar registerProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_screen);
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        goToLoginPage = (TextView) findViewById(R.id.goToLoginPage);
        goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(signUpScreen.this, Login.class));
            }
        });

        // This is for the "Register" Banner for the user to go back to the home screen
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        // This is for the button

        registerName = (EditText) findViewById(R.id.registerName);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerProgressBar = (ProgressBar) findViewById(R.id.registerProgressBar);
        // This is for all the other variable that we have




    }
    public void launchMainScreen(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.banner:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.registerButton:
               registerButton();
               break;
        }
    }

    private void registerButton() {
        String email = registerEmail.getText().toString().trim();
        String name = registerName.getText().toString().trim();
        String password = registerPassword.getText().toString().trim();

        if(email.isEmpty()){
            registerEmail.setError("Please enter a email!");
            registerEmail.requestFocus();
            return;
        }
        if (name.isEmpty()){
            registerName.setError("Please enter your name!");
            registerName.requestFocus();
            return;
        }
        if(password.isEmpty() || password.length() < 6){
            registerPassword.setError("Please enter a valid password!");
            registerName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            registerEmail.setError("Please provide a valid Email!");
            registerEmail.requestFocus();
            return;
        }
        registerProgressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            User user = new User(name, password, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(signUpScreen.this, "User has been registered! Please check your email!", Toast.LENGTH_SHORT).show();
                                        registerProgressBar.setVisibility(View.VISIBLE);
                                        startActivity(new Intent(signUpScreen.this, MainActivity.class));
                                    } else {
                                        Toast.makeText(signUpScreen.this, "Failed to register User!", Toast.LENGTH_SHORT).show();
                                        registerProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(signUpScreen.this, "Failed to register User!", Toast.LENGTH_SHORT).show();
                            registerProgressBar.setVisibility(View.GONE);
                        }
                    }
                });

    }
}