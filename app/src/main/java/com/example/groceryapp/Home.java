package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class Home extends Fragment {

    String [] TopItems,PopularItems;
    String    TopItemsRating;
    int [] TopItemsPrices,TopItemImages,PopularItemsPrices,PopularItemImages;

    Context context;

    RecyclerView TopItemsRecycleView;
    ImageView mFruits,mVeggies,mSpices,mJuices;
    TextView mPopularItemName,mPopularItemRating;
            ImageView mPopularItemImage;
    Button mAtcButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = getContext();

        /* Set Category on Click */
        mFruits = view.findViewById(R.id.fruit_category);
        mFruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayAll.class);
                intent.putExtra("title","Fruits");
                context.startActivity(intent);
            }
        });

        mVeggies = view.findViewById(R.id.veggies_category);
        mVeggies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayAll.class);
                intent.putExtra("title","Veggies");
                context.startActivity(intent);

            }
        });

        mSpices = view.findViewById(R.id.spices_category);
        mSpices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayAll.class);
                intent.putExtra("title","Spices");
                context.startActivity(intent);

            }
        });


        mJuices = view.findViewById(R.id.juices_category);
        mJuices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayAll.class);
                intent.putExtra("title","Juices");
                context.startActivity(intent);

            }
        });


        /* Set Random Popular Item */



        PopularItems = getResources().getStringArray(R.array.PopularItems);
        int popularItemCount = PopularItems.length;
        PopularItemsPrices = getResources().getIntArray(R.array.PopularItemPrices);
        PopularItemImages = new int[]{R.drawable.apple,R.drawable.cinnamon,R.drawable.tomato,R.drawable.carrot};


        Random random = new Random();
        int randomItemPosition = random.nextInt(popularItemCount);

        mPopularItemName = view.findViewById(R.id.PopularItemName);
        mPopularItemName.setText(PopularItems[randomItemPosition]);

        mPopularItemImage = view.findViewById(R.id.PopularItemImage);
        mPopularItemImage.setImageResource(PopularItemImages[randomItemPosition]);

        mPopularItemRating = view.findViewById(R.id.PopularItemRating);
        mPopularItemRating.setText( getResources().getString(R.string.TopRating));

        mAtcButton = view.findViewById(R.id.atcPopularItem);
        mAtcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayItem.class);
                intent.putExtra("itemName",mPopularItemName.getText());
                intent.putExtra("itemDescription",getResources().getString(R.string.description));
                intent.putExtra("itemPrice",PopularItemsPrices[randomItemPosition]);
                intent.putExtra("itemImage",PopularItemImages[randomItemPosition]);
                intent.putExtra("itemRating",mPopularItemRating.getText());

                context.startActivity(intent);
            }
        });










        /* Adding Items to Top Item Recycler View*/

        TopItems = getResources().getStringArray(R.array.TopRatedItems);
        TopItemsRating = getResources().getString(R.string.TopRating);
        TopItemsPrices = getResources().getIntArray(R.array.TopRatedPrices);
        TopItemImages = new int[]{R.drawable.apple,R.drawable.potato,R.drawable.cinnamon,R.drawable.cardamom,R.drawable.guava,
                R.drawable.garlic,R.drawable.carrot};

        TopItemsRecycleView = view.findViewById(R.id.TopItemsRecyclerView);

        ArrayList<Item> TopItemList = new ArrayList<>();
        for (int i = 0; i <TopItems.length ; i++) {

            TopItemList.add(new Item(TopItems[i],getResources().getString(R.string.description),TopItemsRating,TopItemsPrices[i],TopItemImages[i]));

        }

        TopItemAdapter topItemAdapter = new TopItemAdapter(context,TopItemList);
        TopItemsRecycleView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        TopItemsRecycleView.setAdapter(topItemAdapter);











        return view;


    }



}