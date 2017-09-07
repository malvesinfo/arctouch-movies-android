package com.arctouch.movies.serverapi;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Interceptor;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class ClientApiConfig {

    private static RequestLogLevel logLevel = RequestLogLevel.NONE;
    private static List<Interceptor> interceptors = new ArrayList<>();
    private static List<Interceptor> networkInterceptor = new ArrayList<>();

    public static void setLogLevel(@NonNull final RequestLogLevel logLevel) {
        ClientApiConfig.logLevel = logLevel;
    }

    public static void addInterceptors(@Nullable final Interceptor... interceptors) {
        if (interceptors != null) {
            Collections.addAll(ClientApiConfig.interceptors, interceptors);
        }
    }

    public static void addNetworkInterceptor(@Nullable final Interceptor... interceptors) {
        if (interceptors != null) {
            Collections.addAll(ClientApiConfig.networkInterceptor, interceptors);
        }
    }

    @NonNull
    public static RequestLogLevel logLevel() {
        return logLevel;
    }

    @NonNull
    public static List<Interceptor> interceptors() {
        return interceptors;
    }

    @NonNull
    public static List<Interceptor> networkInterceptor() {
        return networkInterceptor;
    }

    @NonNull
    public static String headerAuthorization(String token) {
        return "Bearer " + token;
    }
}
