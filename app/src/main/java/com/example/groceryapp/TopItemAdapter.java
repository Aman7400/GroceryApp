package com.example.groceryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopItemAdapter extends RecyclerView.Adapter<TopItemAdapter.TopItemViewHolder> {

    private ArrayList<Item> TopItems;
    private Context context;

    public TopItemAdapter(Context context,ArrayList<Item> topItems) {
        this.context = context;
        TopItems = topItems;
    }

    @NonNull
    @Override
    public TopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
       View view =  inflater.inflate(R.layout.top_item_card,parent,false);

        return new TopItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopItemViewHolder holder, int position) {

        holder.mTopItemName.setText(TopItems.get(position).getItemName());
        holder.mTopItemImage.setImageResource(TopItems.get(position).getItemImage());
        holder.mTopItemPrice.setText("\u20B9"+TopItems.get(position).getItemPrice());
        holder.mTopItemCardHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayItem.class);
                intent.putExtra("itemName",TopItems.get(position).getItemName());
                intent.putExtra("itemDescription",TopItems.get(position).getItemDescription());
                intent.putExtra("itemPrice",TopItems.get(position).getItemPrice());
                intent.putExtra("itemImage",TopItems.get(position).getItemImage());
                intent.putExtra("itemRating",TopItems.get(position).getItemRating());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return TopItems.size();
    }

    public class TopItemViewHolder extends RecyclerView.ViewHolder {

        TextView mTopItemName,mTopItemPrice;
        LinearLayout mTopItemCardHolder;
        ImageView mTopItemImage;

        public TopItemViewHolder(@NonNull View itemView) {
            super(itemView);

            mTopItemName = itemView.findViewById(R.id.TopItemName);
            mTopItemPrice = itemView.findViewById(R.id.TopItemPrice);
            mTopItemImage = itemView.findViewById(R.id.TopItemImage);
            mTopItemCardHolder = itemView.findViewById(R.id.TopItemCard);

        }
    }

}
