package com.example.groceryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class Profile extends Fragment {


    private TextView mProfileName , mProfileEmail;
    private Button mLogOutUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private String mUserID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mProfileName = view.findViewById(R.id.profileName);
        mProfileEmail = view.findViewById(R.id.profileEmail);


        firebaseAuth = FirebaseAuth.getInstance();
        mUserID = firebaseAuth.getUid();

        db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("users").document(mUserID);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                mProfileName.setText(value.getString("fName") +" "+ value.get("lName"));
                mProfileEmail.setText(value.getString("email"));

            }
        });






        /*Sign Out Current Logged User*/
        mLogOutUser = view.findViewById(R.id.logoutUser);
        mLogOutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(),Login.class));

                ((MainActivity)getActivity()).finish();


            }
        });



        return view;
    }
}