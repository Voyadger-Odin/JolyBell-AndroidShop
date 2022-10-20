package com.voyager.jolybell.user_settings;

public class UserSettings {
    public enum Currency{
        USD, EUR, RUB, UAH
    }
    public enum Language{
        EN, RU, UKR
    }

    public static Currency currency = Currency.RUB;
    public static Language language = Language.RU;

    public static String CurrencyToString(Currency currency){
        if(currency == Currency.USD){return "USD";}
        if(currency == Currency.EUR){return "EUR";}
        if(currency == Currency.RUB){return "RUB";}
        if(currency == Currency.UAH){return "UAH";}
        return "";
    }
    public static String CurrencyToString(){
        return CurrencyToString(currency);
    }
    public static String LanguageToString(){
        if(language == Language.EN){return "АНГЛ";}
        if(language == Language.RU){return "РУС";}
        if(language == Language.UKR){return "УКР";}
        return "";
    }

    public static Currency[] Currencies(){
        UserSettings.Currency[] arrC = {
                UserSettings.Currency.USD,
                UserSettings.Currency.EUR,
                UserSettings.Currency.RUB,
                UserSettings.Currency.UAH
        };
        return arrC;
    }
}
