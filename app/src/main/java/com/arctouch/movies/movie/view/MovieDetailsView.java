package com.arctouch.movies.movie.view;

import android.support.annotation.NonNull;

import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.model.MovieDetails;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public interface MovieDetailsView {

    void showLoading();

    void hideLoading();

    void showData(@NonNull MovieDetails movie);

    void showFetchError();

    void showPreData(@NonNull Movie movie);

    void setFavoriteMovie();

}
