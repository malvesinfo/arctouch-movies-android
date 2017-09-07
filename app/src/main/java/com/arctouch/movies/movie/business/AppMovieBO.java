package com.arctouch.movies.movie.business;

import android.support.annotation.NonNull;

import com.arctouch.movies.movie.dao.AppMovieDAO;
import com.arctouch.movies.movie.dao.MovieDAO;
import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.model.MovieDetails;
import com.arctouch.movies.serverapi.response.MovieResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class AppMovieBO implements MovieBO {

    private final MovieDAO movieDAO;

    public static MovieBO newInstance() {
        return new AppMovieBO(AppMovieDAO.newInstance());
    }

    private AppMovieBO(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    @Override
    public Observable<MovieResponse> fetchMovies(@NonNull String name, int page) {
        return movieDAO.fetchMovies(name, page);
    }

    @Override
    public Observable<MovieResponse> fetchPopularMovies(int page) {
        return movieDAO.fetchPopularMovies(page);
    }

    @Override
    public Observable<MovieDetails> fetchMovieDetails(long id) {
        return movieDAO.fetchMovieDetails(id);
    }

    @Override
    public Completable save(@NonNull Movie movie) {
        return movieDAO.save(movie);
    }

    @Override
    public Completable remove(@NonNull Movie movie) {
        return movieDAO.remove(movie);
    }

    @Override
    public Observable<List<Movie>> loadMovies() {
        return movieDAO.loadMovies();
    }

    @Override
    public boolean hasMovie(@NonNull Movie movie) {
        return movieDAO.hasMovie(movie);
    }
}
