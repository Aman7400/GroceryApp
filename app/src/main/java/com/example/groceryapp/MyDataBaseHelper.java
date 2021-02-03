package com.example.groceryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DB_NAME = "AddedItems.db";
    private static final int DB_VERSION = 1;



    private static final String TABLE_NAME = "Orders";
    private static final String COL_ID = "id";
    private static final String COL_ITEM_NAME = "Item_Name";
    private static final String COL_COST = "Total_Cost";


    public MyDataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + COL_ITEM_NAME + " TEXT, " +
                        COL_COST + " INTEGER);";

        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }

    public void AddItems(String ItemName, int ItemCost){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ITEM_NAME,ItemName);
        cv.put(COL_COST,ItemCost);

        long result  =  db.insert(TABLE_NAME,null,cv);

        if(result == -1){
            Toast.makeText(context, "Item Addition Failed", Toast.LENGTH_SHORT).show();
         } else{
            Toast.makeText(context, "Item Addition Successfully", Toast.LENGTH_SHORT).show();
         }


     }

     Cursor ShowItems(){

        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;

        if(db != null){
        cursor =   db.rawQuery(query,null);
        }

        return  cursor;


    }

    void deleteItemRow(String id){

        SQLiteDatabase db = this.getWritableDatabase();
      long result  =  db.delete(TABLE_NAME,"id=?",new String[]{id});
      if(result == -1){
          Toast.makeText(context, "Item not removed", Toast.LENGTH_SHORT).show();
      }else{
          Toast.makeText(context, "Item removed Successfully", Toast.LENGTH_SHORT).show();
      }

    }

    void deleteAllItems(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }

    public int totalSum() {
        SQLiteDatabase db = this.getWritableDatabase();
        int total = 0;

        Cursor cursor = db.rawQuery("SELECT SUM(" + COL_COST + ") as Total FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {

            total = cursor.getInt(cursor.getColumnIndex("Total"));
        }
        return total;
    }


}
