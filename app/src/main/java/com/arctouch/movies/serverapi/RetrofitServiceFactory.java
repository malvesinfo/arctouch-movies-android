package com.arctouch.movies.serverapi;

import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;

import io.reactivex.schedulers.Schedulers;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.TlsVersion;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class RetrofitServiceFactory {

    public static Retrofit build(@NonNull final String baseUrl, @NonNull final List<Interceptor> interceptors) {
        final OkHttpClient.Builder okBuilder = new OkHttpClient().newBuilder();

        final List<Interceptor> appInterceptors = ClientApiConfig.interceptors();
        for (final Interceptor interceptor : appInterceptors) {
            okBuilder.addInterceptor(interceptor);
        }

        for (final Interceptor interceptor : interceptors) {
            okBuilder.addInterceptor(interceptor);
        }

        final List<Interceptor> networkInterceptors = ClientApiConfig.networkInterceptor();
        for (final Interceptor interceptor : networkInterceptors) {
            okBuilder.addNetworkInterceptor(interceptor);
        }

        final RequestLogLevel logLevel = ClientApiConfig.logLevel();
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(logLevel.toInterceptorLogLevel());
        okBuilder.addInterceptor(logging);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                    .tlsVersions(TlsVersion.TLS_1_2)
                    .cipherSuites(CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
                    .build();
            okBuilder.connectionSpecs(Collections.singletonList(spec));
        }

        final Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(baseUrl);
        builder.client(okBuilder.build());

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()));
        builder.addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public static <T> T create(@NonNull final Class<T> clazz, @NonNull final String baseUrl) {
        return create(clazz, baseUrl, Collections.emptyList());
    }

    public static <T> T create(@NonNull final Class<T> clazz, @NonNull final String baseUrl,
                               @NonNull final List<Interceptor> interceptors) {
        return build(baseUrl, interceptors).create(clazz);
    }
}
