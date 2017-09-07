package com.arctouch.movies.base.view;

import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.components.support.RxFragment;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class BaseFragment extends RxFragment {

    @NonNull
    protected <T> ObservableTransformer<T, T> composeUi() {
        return observable -> observable.observeOn(AndroidSchedulers.mainThread()).compose(bindToLifecycle());
    }

}