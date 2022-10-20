package com.voyager.jolybell.model.basket;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.voyager.jolybell.model.CatalogItems;

import java.util.ArrayList;

public class Basket {

    private static int unicalIdNext = 0;
    public static ArrayList<BasketChanged> listeners = new ArrayList<>();

    public static ArrayList<ItemInBasket> items = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void addItemInBasket(ItemInBasket item){
        item.unicalId = unicalIdNext;
        items.add(item);

        unicalIdNext++;

        change();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void removeItemInBasket(int position){
        items.remove(position);

        if (items.size() == 0){
            unicalIdNext = 0;
        }

        change();
    }

    public static void cleanBasket(){
        items = new ArrayList<>();
        unicalIdNext = 0;
    }

    public static int findItemByUnicalId(int unicalId){
        for (int i=0; i<items.size(); i++) {
            if(items.get(i).unicalId == unicalId){
                return i;
            }
        }
        return -1;
    }

    public static int getItemsCount(){
        int count = 0;
        for(int i=0; i<items.size(); i++){
            count += items.get(i).countItems;
        }
        return count;
    }
    public static float getItemsCost(){
        float cost = 0;
        for(int i=0; i<items.size(); i++){
            cost += CatalogItems.items.get(items.get(i).itemId).getCost()
                    * 100
                    * items.get(i).countItems
                    / 100
            ;
        }
        return cost;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void change(){
        listeners.forEach(BasketChanged::basketChanged);
    }
}