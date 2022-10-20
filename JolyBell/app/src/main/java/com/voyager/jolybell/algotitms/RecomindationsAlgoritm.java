package com.voyager.jolybell.algotitms;

import com.voyager.jolybell.model.CatalogItems;

import java.util.ArrayList;

public class RecomindationsAlgoritm {
    public static ArrayList<Integer> recommend(int itemId){
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i=0; i<CatalogItems.items.size(); i++){
            if(i != itemId){
                list.add(i);
            }
        }

        return list;
    }
}
