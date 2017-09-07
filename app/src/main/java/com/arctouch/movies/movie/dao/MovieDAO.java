package com.arctouch.movies.movie.dao;

import android.support.annotation.NonNull;

import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.model.MovieDetails;
import com.arctouch.movies.serverapi.response.MovieResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public interface MovieDAO {

    Observable<MovieResponse> fetchMovies(@NonNull String name, int page);

    Observable<MovieResponse> fetchPopularMovies(int page);

    Observable<MovieDetails> fetchMovieDetails(long id);

    Completable save(@NonNull Movie movie);

    Completable remove(@NonNull Movie movie);

    Observable<List<Movie>> loadMovies();

    boolean hasMovie(@NonNull Movie movie);

}
