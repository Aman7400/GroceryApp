package com.example.groceryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckOut extends AppCompatActivity {

    private Button mPay;
    private TextView mAmount;
    private String amount,userId,orderedItems,orderId;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);


        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        userId = firebaseAuth.getUid();



        amount = Integer.toString(getIntent().getIntExtra("amount",0));
        orderedItems = getIntent().getStringExtra("orderedItems");



        mAmount = findViewById(R.id.PayableAmount);
        mAmount.setText("\u20b9"+amount);


        mPay = findViewById(R.id.pay);
        mPay.setText("Pay \u20b9"+amount);
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Getting the current date
                Date date = new Date();
                //This method returns the time in millis
                long timeMilli = date.getTime();


                orderId = "Order" + timeMilli;


                DocumentReference documentReference = db.collection("orders").document(userId)
                        .collection("All orders").document(orderId);




                Map <String,Object> orderDetails = new HashMap<>();
                orderDetails.put("Date",date);
                orderDetails.put("amount",amount);
                orderDetails.put("items",orderedItems);



                documentReference.set(orderDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        /* Clear Cart */
                        clearCart();


                        Toast.makeText(CheckOut.this, "Order Placed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CheckOut.this,MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CheckOut.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });






            }
        });


    }

    void clearCart(){

        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(CheckOut.this);
        myDataBaseHelper.deleteAllItems();

    }

}