package com.voyager.jolybell.algotitms;

import com.voyager.jolybell.constants_data.AlertStrings_RU;
import com.voyager.jolybell.model.AlertBody;

import java.util.ArrayList;

public class AlertManager {

    public static ArrayList<AlertBody> alertItems;

    public static String getSizeChartTextForItemByCategory(int category){

        AlertBody alert = getAlertBodyByLanguage();
        if(category == 0
        || category == 1){
            return alert.getSizechart_text();
        }
        return "";
    }

    public static ArrayList<String> getRecomendation(){
        return alertItems.get(0).getRecomendation();
    }

    public static AlertBody getAlertBodyByLanguage(){
        return alertItems.get(0);
    }

    public static void IdentifyLanguages(){
        alertItems = new ArrayList();
        alertItems.add(new AlertStrings_RU());
    }
}
