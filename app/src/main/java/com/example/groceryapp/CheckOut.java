package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CheckOut extends AppCompatActivity {

    private Button mPay;
    private TextView mAmount;
    private String amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        amount = Integer.toString(getIntent().getIntExtra("amount",0));


        mAmount = findViewById(R.id.PayableAmount);
        mAmount.setText("\u20b9"+amount);


        mPay = findViewById(R.id.pay);
        mPay.setText("Pay \u20b9"+amount);
        mPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /* Clear Cart */
                clearCart();

                Intent intent = new Intent(CheckOut.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);
                Toast.makeText(CheckOut.this, "Order Placed Successfully", Toast.LENGTH_SHORT).show();


            }
        });


    }

    void clearCart(){

        MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(CheckOut.this);
        myDataBaseHelper.deleteAllItems();

    }

}