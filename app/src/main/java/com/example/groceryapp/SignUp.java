package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class SignUp extends AppCompatActivity {

    private Context context;

    private EditText mFirstName, mLastName, mEmail, mMobileNo, mPassword;
    private TextView mHelpText;
    private Button mSignUp;
    private FirebaseAuth firebaseAuth;
    private ProgressBar progressBar;


    String firstName, lastName, phoneNo, email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign Up");

        /* Hold Context */
        context = SignUp.this;

        mFirstName = findViewById(R.id.fname);
        mLastName = findViewById(R.id.lname);
        mEmail = findViewById(R.id.email);
        mMobileNo = findViewById(R.id.phoneNo);
        mPassword = findViewById(R.id.password);
        mSignUp = findViewById(R.id.SignUp);

        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();


        /* Check if the User is logged in */
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(context,MainActivity.class));
            finish();
        }

        /* Validate Inputs */
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = mFirstName.getText().toString().trim();
                lastName = mLastName.getText().toString().trim();
                phoneNo = mMobileNo.getText().toString().trim();
                 email = mEmail.getText().toString().trim();
                 password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(firstName)){
                    mFirstName.setError("First Name is Required");
                    return;
                }

                if(TextUtils.isEmpty(phoneNo)){
                    mMobileNo.setError("Mobile No is Required");
                    return;
                }

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(context, "Account Created", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(context,MainActivity.class));
                                    finish();

                                }else{
                                    progressBar.setVisibility(View.INVISIBLE);
                                    Toast.makeText(context, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        }
                );



            }
        });





        /* SignIn Help text  */
        mHelpText  = findViewById(R.id.signUpHelpText);
        mHelpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });

    }
}