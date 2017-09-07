package com.arctouch.movies.serverapi;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public enum RequestLogLevel {
    /**
     * No logs.
     */
    NONE,
    /**
     * Logs request and response lines.
     */
    BASIC,
    /**
     * Logs request and response lines and their respective headers.
     */
    HEADERS,
    /**
     * Logs request and response lines and their respective headers and bodies (if present).
     */
    BODY;

    public HttpLoggingInterceptor.Level toInterceptorLogLevel() {
        switch (this) {
            case NONE:
                return HttpLoggingInterceptor.Level.NONE;
            case BASIC:
                return HttpLoggingInterceptor.Level.BASIC;
            case HEADERS:
                return HttpLoggingInterceptor.Level.HEADERS;
            case BODY:
                return HttpLoggingInterceptor.Level.BODY;
            default:
                throw new IllegalStateException("A RequestLogLevel must be defined on init().");
        }
    }
}
