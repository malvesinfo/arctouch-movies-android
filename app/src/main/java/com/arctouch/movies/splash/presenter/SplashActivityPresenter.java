package com.arctouch.movies.splash.presenter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arctouch.movies.splash.view.SplashView;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class SplashActivityPresenter extends SplashPresenter {

    private final SplashView view;

    public static SplashPresenter newInstance(@NonNull LifecycleProvider provider, @NonNull SplashView view) {
        return new SplashActivityPresenter(provider, view);
    }

    private SplashActivityPresenter(@NonNull LifecycleProvider provider, @NonNull SplashView view) {
        super(provider);
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Handler handler = new Handler();
        handler.postDelayed(view::startHomeView, 3200);
    }
}
