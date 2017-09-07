package com.arctouch.movies.home.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arctouch.movies.base.presenter.RxBasePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class HomePresenter extends RxBasePresenter {


    public <T> HomePresenter(@NonNull LifecycleProvider<T> provider) {
        super(provider);
    }

    public abstract void startSearch(@NonNull String name);

    public abstract void loadMore(@Nullable String search);
}
