package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import java.util.Objects;

public class Login extends AppCompatActivity {


    private EditText mLoginEmail, mLoginPassword;
    private TextView mHelpText;
    private FirebaseAuth firebaseAuth;
    private ProgressBar mProgressBar;
    private Button mSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign In");


        /* Get Email and Password*/
        mLoginEmail = findViewById(R.id.login_email);

        mLoginPassword = findViewById(R.id.login_password);

        mProgressBar = findViewById(R.id.progressBar2);

        firebaseAuth = FirebaseAuth.getInstance();





        mSignIn = findViewById(R.id.signIn);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loginEmail = mLoginEmail.getText().toString().trim();
                String loginPassword = mLoginPassword.getText().toString().trim();


                if(TextUtils.isEmpty(loginEmail)){
                    mLoginEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(loginPassword)){
                    mLoginPassword.setError("Password is required");
                    return;
                }

                mProgressBar.setVisibility(View.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(loginEmail,loginPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));
                            finish();
                        }else {
                            Toast.makeText(Login.this, "Error :" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });

            }
        });








        /* Login Help Text */
        mHelpText = findViewById(R.id.loginHelpText);
        mHelpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this,SignUp.class));

            }
        });


    }
}