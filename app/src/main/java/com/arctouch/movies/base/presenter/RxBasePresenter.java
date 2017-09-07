package com.arctouch.movies.base.presenter;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.CheckReturnValue;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class RxBasePresenter extends BasePresenter {

    @NonNull
    private final LifecycleProvider provider;

    protected <T> RxBasePresenter(@NonNull LifecycleProvider<T> provider) {
        super();
        this.provider = provider;
    }

    @NonNull
    protected <T> ObservableTransformer<T, T> composeUi() {
        return observable -> observable.observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle());
    }

    @NonNull
    @CheckReturnValue
    public Observable lifecycle() {
        return provider.lifecycle();
    }

    @NonNull
    @CheckReturnValue
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return provider.bindToLifecycle();
    }

    @NonNull
    @CheckReturnValue
    public <T> LifecycleTransformer<T> bindUntilEvent(@NonNull final T event) {
        return provider.bindUntilEvent(event);
    }
}
