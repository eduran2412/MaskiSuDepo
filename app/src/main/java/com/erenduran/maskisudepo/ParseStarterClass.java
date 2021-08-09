package com.erenduran.maskisudepo;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("mF2gHB1GW2NtH9g4DzV08CAz685DdCm4bPVQZ0FO")
        .clientKey("zjsiwzySBSwW4qKkPkbtW2h8cyiK1jArnM1Whysa")
        .server("https://parseapi.back4app.com/")
        .build());
        // parse ı kullanacağımız sunucudan aldığımız ve sunucu ile projeyi birbirine bağlayan değişkenler
        // bunları kuracağımız server dan alacağız ve buraya yapıştıracağız
    }
}
