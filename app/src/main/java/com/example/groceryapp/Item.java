package com.example.groceryapp;

public class Item {
    private String mItemName,mItemDescription,mItemRating;
  private   int mItemPrice, mItemImage;


    public Item(String mItemName, String mItemDescription, String mItemRating, int mItemPrice, int mItemImage) {
        this.mItemName = mItemName;
        this.mItemDescription = mItemDescription;
        this.mItemRating = mItemRating;
        this.mItemPrice = mItemPrice;
        this.mItemImage = mItemImage;
    }

    public int getItemPrice() {
        return mItemPrice;
    }

    public String getItemDescription() {
        return mItemDescription;
    }

    public String getItemName() {
        return mItemName;
    }

    public String getItemRating() {
        return mItemRating;
    }

    public int getItemImage() {
        return mItemImage;
    }
}

