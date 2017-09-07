package com.arctouch.movies.movie.presenter;

import android.support.annotation.NonNull;

import com.arctouch.movies.base.presenter.RxBasePresenter;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class MovieDetailsPresenter extends RxBasePresenter {

    public <T> MovieDetailsPresenter(@NonNull LifecycleProvider<T> provider) {
        super(provider);
    }

    public abstract void saveFavorite(@NonNull boolean favorite);
}
