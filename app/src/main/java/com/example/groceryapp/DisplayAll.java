package com.example.groceryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class DisplayAll extends AppCompatActivity {

    String  [] category,rating;
    int [] price, images;
    RecyclerView DisplayAllRecyclerView ;
    ArrayList <Item> DisplayAllItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        /* Set Title */
        Intent intent = getIntent();
         String title =  intent.getStringExtra("title");
        this.setTitle(title);


        /* Add Items in List */
        switch (title){
            case "Fruits" :
                category = getResources().getStringArray(R.array.Fruits);
                images = new int[]{R.drawable.apple,R.drawable.guava,
                        R.drawable.orange,R.drawable.grapes,
                        R.drawable.watermelon};
                price = getResources().getIntArray(R.array.FruitsPrices);

                break;
            case "Veggies" :
                category = getResources().getStringArray(R.array.Veggies);
                images = new int[]{R.drawable.tomato,R.drawable.potato,
                        R.drawable.ginger,R.drawable.garlic,
                        R.drawable.carrot};
                price = getResources().getIntArray(R.array.VeggiesPrices);



                break;
            case "Juices" :
                category = getResources().getStringArray(R.array.Juices);
                images = new int[]{R.drawable.orange_juice,R.drawable.apple_juice,
                        R.drawable.lemon_juice,R.drawable.carrot_juice
                        };
                price = getResources().getIntArray(R.array.JuicesPrices);


                break;
            case "Spices" :
                category = getResources().getStringArray(R.array.Spices);
                images = new int[]{R.drawable.turmeric,R.drawable.cumin,
                        R.drawable.cinnamon,R.drawable.cardamom};
                price = getResources().getIntArray(R.array.SpicesPrices);


                break;
            default:
                throw new IllegalStateException("Unexpected value: " + title);
        }

        rating = getResources().getStringArray(R.array.Rating);


        DisplayAllRecyclerView = findViewById(R.id.DisplayAllRecyclerView);

        DisplayAllItem = new ArrayList<>();
        for (int i = 0; i <category.length ; i++) {

            DisplayAllItem.add(new Item(category[i],getResources().getString(R.string.description),rating[i],price[i],images[i]));


        }

        DisplayAllRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        DisplayAllRecyclerView.setAdapter(new DisplayAllAdapter(this,DisplayAllItem));









    }



}