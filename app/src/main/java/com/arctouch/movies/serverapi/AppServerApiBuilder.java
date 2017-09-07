package com.arctouch.movies.serverapi;

import android.support.annotation.NonNull;

import com.arctouch.movies.BuildConfig;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class AppServerApiBuilder {

    public AppServerApiBuilder() {
    }

    @NonNull
    public AppServerApi build() {
        AppServerApi api = RetrofitServiceFactory.create(AppServerApi.class, BuildConfig.BASE_URL);
        return api;
    }
}
