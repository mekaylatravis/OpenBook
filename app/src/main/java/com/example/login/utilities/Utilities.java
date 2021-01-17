package com.example.login.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilities {
    public static String long_data(long time){
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MM yyyy 'at' hh:mm aaa", Locale.getDefault());
        return dateFormat.format(new Date(time));
    }
}
