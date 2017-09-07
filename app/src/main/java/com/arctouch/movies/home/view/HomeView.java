package com.arctouch.movies.home.view;

import com.arctouch.movies.movie.model.Movie;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public interface HomeView {

    void showMovies(List<Movie> movieList);

    void showFavoriteMovies(List<Movie> movieList);

    void showLoading();

    void hideLoading();

    void showFetchError();

    void showHasNoMoreData();

}
