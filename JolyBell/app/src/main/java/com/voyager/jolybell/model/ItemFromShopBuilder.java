package com.voyager.jolybell.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.voyager.jolybell.user_settings.UserSettings;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemFromShopBuilder{
    ItemFromShop itemFromShop;
    public ItemFromShopBuilder(){
        itemFromShop = new ItemFromShop();
        itemFromShop.sizes = new ArrayList<>();
    }

    public ItemFromShopBuilder setItemId(int itemId) {
        itemFromShop.itemId = itemId;
        return this;
    }

    public ItemFromShopBuilder setName(String name) {
        itemFromShop.name = name;
        return this;
    }

    public ItemFromShopBuilder setImg(ArrayList<String> img) {
        itemFromShop.img = img;
        return this;
    }

    public ItemFromShopBuilder setSizes(ArrayList<String> sizes) {
        itemFromShop.sizes = sizes;
        return this;
    }

    public ItemFromShopBuilder setDescription(String description) {
        itemFromShop.description = description;
        return this;
    }

    public ItemFromShopBuilder setCategoryId(int categoryId) {
        itemFromShop.categoryId = categoryId;
        return this;
    }

    public ItemFromShopBuilder setCosts(float[] costs) {
        UserSettings.Currency[] arrC = UserSettings.Currencies();
        itemFromShop.costs = new HashMap<>();
        for(int i=0; i<costs.length; i++){
            itemFromShop.costs.put(UserSettings.CurrencyToString(arrC[i]), costs[i]);
        }
        return this;
    }

    public ItemFromShopBuilder setTheme(ItemFromShop.Theme theme) {
        itemFromShop.theme = theme;
        return this;
    }

    public ItemFromShop build(){return itemFromShop;}
}