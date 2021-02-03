package com.example.groceryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddedItemAdapter extends RecyclerView.Adapter<AddedItemAdapter.AddedItemViewHolder> {

    private ArrayList<String> itemIds, itemNames, itemsAmount;
    private Context context;


    public AddedItemAdapter(Context context,ArrayList<String> itemIds,ArrayList<String> itemNames,ArrayList<String> itemsAmount) {
        this.itemIds = itemIds;
        this.context = context;
        this.itemNames = itemNames;
        this.itemsAmount = itemsAmount;
    }




    @NonNull
    @Override
    public AddedItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
      View view =  layoutInflater.inflate(R.layout.added_item,parent,false);

        return new AddedItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedItemViewHolder holder, int position) {



        holder.mItemId.setText(String.valueOf(position+1)+".");
        holder.mItemName.setText(itemNames.get(position));
        holder.mAmount.setText("\u20B9"+itemsAmount.get(position));

        holder.mItemCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                deleteItemConfirmation(position);




                return true;

            }
        });
    }

    @Override
    public int getItemCount() {
        return itemNames.size();
    }

    public class AddedItemViewHolder extends RecyclerView.ViewHolder {


        TextView mItemId, mItemName, mAmount,mSum;
        LinearLayout mItemCard;

        public AddedItemViewHolder(@NonNull View itemView) {
            super(itemView);


            mItemId = itemView.findViewById(R.id.AddedItemId);
            mItemName = itemView.findViewById(R.id.AddedItemName);
            mAmount = itemView.findViewById(R.id.AddedItemAmount);
            mSum = itemView.findViewById(R.id.sum);
            mItemCard = itemView.findViewById(R.id.AddedItemCard);








        }
    }

    void deleteItemConfirmation(int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Remove Item");
        builder.setMessage("Do you wanna remove this item from cart ? ");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MyDataBaseHelper myDataBaseHelper = new MyDataBaseHelper(context);
                myDataBaseHelper.deleteItemRow(itemIds.get(position));

                FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();

                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.NavFrameHolder,new Cart());
                ft.commit();









            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();

    }

}
