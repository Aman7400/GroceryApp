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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayAllAdapter extends RecyclerView.Adapter<DisplayAllAdapter.DisplayAllViewHolder> {

   private ArrayList <Item> displayItems;
   private Context context;

    public DisplayAllAdapter(Context context,ArrayList<Item> displayItems ) {
        this.displayItems = displayItems;
        this.context = context;
    }

    @NonNull
    @Override
    public DisplayAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(R.layout.item_card,parent,false);

        return new DisplayAllViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayAllViewHolder holder, int position) {

        holder.itemImage.setImageResource(displayItems.get(position).getItemImage());
        holder.itemName.setText(displayItems.get(position).getItemName());
        holder.itemPrice.setText("\u20B9"+displayItems.get(position).getItemPrice());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayItem.class);
                intent.putExtra("itemName",displayItems.get(position).getItemName());
                intent.putExtra("itemDescription",displayItems.get(position).getItemDescription());
                intent.putExtra("itemPrice",displayItems.get(position).getItemPrice());
                intent.putExtra("itemImage",displayItems.get(position).getItemImage());
                intent.putExtra("itemRating",displayItems.get(position).getItemRating());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return displayItems.size();
    }

    public class DisplayAllViewHolder extends RecyclerView.ViewHolder {

        TextView itemName,itemPrice;
        LinearLayout linearLayout;
        ImageView itemImage;

        public DisplayAllViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.ItemName);
            itemImage = itemView.findViewById(R.id.ItemImage);
            linearLayout = itemView.findViewById(R.id.ItemCard);
            itemPrice = itemView.findViewById(R.id.ItemPrice);
        }
    }
}
