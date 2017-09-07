package com.arctouch.movies;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
