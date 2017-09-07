package com.arctouch.movies.home.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.arctouch.movies.home.view.HomeView;
import com.arctouch.movies.movie.business.AppMovieBO;
import com.arctouch.movies.movie.business.MovieBO;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class HomeActivityPresenter extends HomePresenter {

    private final MovieBO movieBO;
    private final HomeView view;
    private int currentPage;
    private int totalPages;

    public static HomePresenter newInstance(@NonNull LifecycleProvider provider, @NonNull HomeView view) {
        return new HomeActivityPresenter(provider, AppMovieBO.newInstance(), view);
    }

    private HomeActivityPresenter(@NonNull LifecycleProvider provider, @NonNull MovieBO movieBO,
                                  @NonNull HomeView view) {
        super(provider);
        this.movieBO = movieBO;
        this.view = view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fetchPopularMovies();
    }

    @Override
    public void onResume() {
        super.onResume();
        movieBO.loadMovies().compose(composeUi()).subscribe(view::showFavoriteMovies);
    }

    @Override
    public void startSearch(@NonNull String name) {
        currentPage = 0;
        searchMovies(name);
    }

    @Override
    public void loadMore(@Nullable String search) {
        if (currentPage < totalPages) {
            if (!TextUtils.isEmpty(search)) {
                searchMovies(search);
            } else {
                fetchPopularMovies();
            }
        } else {
            view.showHasNoMoreData();
        }
    }

    private void fetchPopularMovies() {
        view.showLoading();
        movieBO.fetchPopularMovies(currentPage + 1).compose(composeUi())
                .subscribe(movieResponse -> {
                    view.hideLoading();
                    totalPages = movieResponse.getTotalPages();
                    currentPage = movieResponse.getPage();
                    view.showMovies(movieResponse.getMovieList());
                }, throwable -> {
                    view.hideLoading();
                    view.showFetchError();
                });
    }

    private void searchMovies(@NonNull String name) {
        view.showLoading();
        movieBO.fetchMovies(name, currentPage + 1).compose(composeUi())
                .subscribe(movieResponse -> {
                    view.hideLoading();
                    totalPages = movieResponse.getTotalPages();
                    currentPage = movieResponse.getPage();
                    view.showMovies(movieResponse.getMovieList());
                }, throwable -> {
                    view.hideLoading();
                    view.showFetchError();
                });
    }
}
