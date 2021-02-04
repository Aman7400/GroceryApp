package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class Cart extends Fragment {

    private Context context;

    RecyclerView mAddedItemRecycleView;
    TextView mSum;
    int sum;

    Button placeOrder;

 MyDataBaseHelper myDataBaseHelper;
 ArrayList<String> itemId, itemName,itemAmount;
 String [] OrderedItemsName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cart, container, false);

        /* Set Context*/
        context = getContext();

        /* Initialize DB */
        myDataBaseHelper = new MyDataBaseHelper(context);

        /* Initialize Data Items */
        itemId = new ArrayList<>();
        itemName = new ArrayList<>();
        itemAmount = new ArrayList<>();

        display();

        /* Check if No item present */
        if(itemId.isEmpty()){

            View emptyCart = inflater.inflate(R.layout.no_item_cart,container,false);
            return emptyCart;

        }else {


            /* Set Added Item RecycleView*/
            mAddedItemRecycleView = view.findViewById(R.id.AddedItemRecyclerView);
            mAddedItemRecycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            mAddedItemRecycleView.setAdapter(new AddedItemAdapter(context,itemId,itemName,itemAmount));


            /* Set total Amount */
            mSum = view.findViewById(R.id.sum);
            MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(context);
            sum =  myDataBaseHelper.totalSum();
            mSum.setText("\u20b9"+sum);

            /* Place Order */
            placeOrder = view.findViewById(R.id.placeOrder);
            placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setPlaceOrder(sum);
                }
            });

            return view;


        }









    }

    void display(){

        Cursor cursor = myDataBaseHelper.ShowItems();
        if(cursor.getCount() == 0){
            Toast.makeText(context, "No Item Added", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                itemId.add(cursor.getString(0));
                itemName.add(cursor.getString(1));
                itemAmount.add(cursor.getString(2));

            }
        }

    }

    void setPlaceOrder(int sum){

        Intent intent = new Intent(context,CheckOut.class);
        intent.putExtra("amount",sum);


        OrderedItemsName = new String[itemName.size()];
        for (int i = 0; i < itemName.size(); i++) {

            OrderedItemsName[i] = itemName.get(i);

        }
        intent.putExtra("orderedItems",Arrays.toString(OrderedItemsName));



        startActivity(intent);

    }

}