package com.voyager.jolybell.model;

import com.voyager.jolybell.user_settings.UserSettings;

import java.util.ArrayList;
import java.util.Map;

public class ItemFromShop {
    public int itemId;
    public String name;
    public ArrayList<String> img;
    public String description;
    public ArrayList<String> sizes;

    public Map<String, Float> costs;

    public int categoryId;

    public Theme theme = Theme.Dark;

    public enum Theme{
        Light, Dark
    }

    public float getCost(UserSettings.Currency currency){
        return costs.get(UserSettings.CurrencyToString(currency));
    }
    public float getCost(){
        return costs.get(UserSettings.CurrencyToString(UserSettings.currency));
    }

    public static String costToString(float cost){
        String res = "";
        int costInt = (int)cost;
//        String costString = costInt + "";
//
//        if(cost >= 1000){
//            //res += ((costInt - (costInt % 1000)) / 1000) + " " + (costInt % 1000);
//            res = costString.substring(0, costString.length() - 3) + " " + costString.substring(costString.length() - 3);
//        }else{
//            res += costInt + "";
//        }
//
//        if((cost - costInt) > 0){
//            res += "." + (int)((cost - costInt) * 10) + "" + + (int)(((cost - costInt) * 100) % 10);
//        }

        if ((cost - costInt) > 0){
            res = cost + "";
        }else{
            res = costInt + "";
        }
        res += " " + UserSettings.CurrencyToString();
        return res;
    }
    public String costToString(){
        return costToString(this.getCost(UserSettings.currency));
    }
}