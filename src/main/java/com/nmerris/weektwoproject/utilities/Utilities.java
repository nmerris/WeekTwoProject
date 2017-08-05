package com.nmerris.weektwoproject.utilities;

import java.util.Locale;
import java.util.ResourceBundle;

public class Utilities {


    public static ResourceBundle setDefaultLocaleToUsSpanish() {
        Locale.setDefault(new Locale("es", "US"));
        System.out.println("*********************************** default locale is now: " + Locale.getDefault());
        return ResourceBundle.getBundle("Messages");
    }



}
