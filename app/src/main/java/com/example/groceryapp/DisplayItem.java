package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayItem extends AppCompatActivity {

    Button mIncreaseQuantity,mDecreaseQuantity, mAddToCart;
    TextView mItemName, mItemPrice, mItemDescription,mItemRating,mTotalAmount, mItemQuantity;
    ImageView mItemImage;
    String ItemName;
    int itemPrice,totalAmount;
    int itemQuantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_item);

        /* Get Item Name */
        mItemName = findViewById(R.id.ItemName);
        mItemName.setText(getIntent().getStringExtra("itemName"));
        ItemName = mItemName.getText().toString().trim();

        setTitle(mItemName.getText());

        /* Get Item Cost*/
        mItemPrice = findViewById(R.id.ItemPrice);
        itemPrice = getIntent().getIntExtra("itemPrice",0);
        mItemPrice.setText("\u20B9"+itemPrice);


        /* Get Item Image */
        mItemImage = findViewById(R.id.ItemImage);
        mItemImage.setImageResource(getIntent().getIntExtra("itemImage",0));

        /* Set Image Description */
        mItemDescription = findViewById(R.id.ItemDescription);
        mItemDescription.setText(getIntent().getStringExtra("itemDescription"));

        /* Set Image Rating */
        mItemRating = findViewById(R.id.ItemRating);
        mItemRating.setText(getIntent().getStringExtra("itemRating"));

        /* Set item Quantity*/
        mItemQuantity = findViewById(R.id.ItemQuantity);
        itemQuantity = Integer.parseInt(mItemQuantity.getText().toString().trim());



        /* Set Total Amount */
        mTotalAmount = findViewById(R.id.TotalItemCost);
        mTotalAmount.setText(""+itemPrice);
        totalAmount =Integer.parseInt(mTotalAmount.getText().toString().trim());







        /* Decrease Quantity */
        mDecreaseQuantity = findViewById(R.id.DecreaseItemQuantity);
        mDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if(itemQuantity>1){
                    mItemQuantity.setText(""+(--itemQuantity));
                     totalAmount = itemQuantity * itemPrice;
                     mTotalAmount.setText(""+(totalAmount));

                }else {
                    Toast.makeText(DisplayItem.this, "Minimum Order : 1kg", Toast.LENGTH_SHORT).show();
                }


            }
        });

        /* Increase Quantity */
        mIncreaseQuantity = findViewById(R.id.IncreaseItemQuantity);
        mIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(itemQuantity<10){
                    mItemQuantity.setText(""+(++itemQuantity));
                    totalAmount = itemQuantity * itemPrice;
                    mTotalAmount.setText(""+(totalAmount));


                }else {
                    Toast.makeText(DisplayItem.this, "Maximum Order : 10kg", Toast.LENGTH_SHORT).show();
                }

            }
        });







        /* Set On Add Item Listener*/
        mAddToCart = findViewById(R.id.atc);
        mAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(DisplayItem.this);
                myDataBaseHelper.AddItems(ItemName,totalAmount);

                finish();


            }
        });

    }
}