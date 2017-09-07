package com.arctouch.movies.splash.presenter;

import android.support.annotation.NonNull;

import com.arctouch.movies.base.presenter.RxBasePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class SplashPresenter extends RxBasePresenter {

    public <T> SplashPresenter(@NonNull LifecycleProvider<T> provider) {
        super(provider);
    }
}
