package com.arctouch.movies.movie.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arctouch.movies.movie.business.AppMovieBO;
import com.arctouch.movies.movie.business.MovieBO;
import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.view.MovieDetailsView;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieDetailsActivityPresenter extends MovieDetailsPresenter {

    private final MovieDetailsView view;
    private final MovieBO movieBO;
    private final Movie movie;

    public static MovieDetailsPresenter newInstance(@NonNull LifecycleProvider provider,
                                                    @NonNull MovieDetailsView view,
                                                    @NonNull Movie movie) {
        return new MovieDetailsActivityPresenter(provider, view, AppMovieBO.newInstance(), movie);
    }

    private MovieDetailsActivityPresenter(@NonNull LifecycleProvider provider,
                                          @NonNull MovieDetailsView view,
                                          @NonNull MovieBO movieBO,
                                          @NonNull Movie movie) {
        super(provider);
        this.view = view;
        this.movieBO = movieBO;
        this.movie = movie;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view.showPreData(movie);
        view.showLoading();

        boolean hasMovie = movieBO.hasMovie(movie);
        if(hasMovie) view.setFavoriteMovie();

        movieBO.fetchMovieDetails(movie.getId()).compose(composeUi())
                .subscribe(movieDetails -> {
                    view.hideLoading();
                    view.showData(movieDetails);
                }, throwable -> {
                    view.hideLoading();
                    view.showFetchError();
                });
    }

    @Override
    public void saveFavorite(@NonNull boolean favorite) {
        if (favorite) {
            movieBO.save(movie).subscribe(() -> {}, throwable -> {});
        } else {
            movieBO.remove(movie).subscribe(() -> {}, throwable -> {});
        }
    }
}
