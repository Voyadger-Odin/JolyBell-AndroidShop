package com.voyager.jolybell.model;

import java.util.ArrayList;

public class CatalogItems {
    public static ArrayList<ItemFromShop> items;
    public static ArrayList<ItemFromShop> itemsShow;

    public static int findItemPositionByItemId(int itemId){
        for(int i=0; i<items.size(); i++){
            if(items.get(i).itemId == itemId){
                return i;
            }
        }
        return -1;
    }
}
