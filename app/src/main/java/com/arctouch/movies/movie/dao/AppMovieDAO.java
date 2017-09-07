package com.arctouch.movies.movie.dao;

import android.support.annotation.NonNull;

import com.arctouch.movies.BuildConfig;
import com.arctouch.movies.movie.model.Movie;
import com.arctouch.movies.movie.model.MovieDetails;
import com.arctouch.movies.serverapi.AppServerApi;
import com.arctouch.movies.serverapi.AppServerApiBuilder;
import com.arctouch.movies.serverapi.response.MovieResponse;

import java.util.List;
import java.util.Locale;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class AppMovieDAO implements MovieDAO {

    private final AppServerApi serverApi;
    private final MovieLocalDAO movieLocalDAO;

    public static MovieDAO newInstance() {
        return new AppMovieDAO(new AppServerApiBuilder().build(), new MovieLocalDAO());
    }

    private AppMovieDAO(AppServerApi serverApi, MovieLocalDAO movieLocalDAO) {
        this.serverApi = serverApi;
        this.movieLocalDAO = movieLocalDAO;
    }

    @Override
    public Observable<MovieResponse> fetchMovies(@NonNull String name, int page) {
        return serverApi.fetchMovies(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), name, page, false);
    }

    @Override
    public Observable<MovieResponse> fetchPopularMovies(int page) {
        return serverApi.fetchPopularMovies(BuildConfig.API_KEY, Locale.getDefault().getLanguage(), page);
    }

    @Override
    public Completable save(@NonNull Movie movie) {
        return Completable.fromCallable(() -> {
            movieLocalDAO.save(movie);
            return true;
        }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Completable remove(@NonNull Movie movie) {
        return Completable.fromCallable(() -> {
            movieLocalDAO.remove(movie);
            return true;
        }).subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<Movie>> loadMovies() {
        return Observable.fromCallable(movieLocalDAO::get).subscribeOn(Schedulers.newThread());
    }

    @Override
    public boolean hasMovie(@NonNull Movie movie) {
        return Observable.fromCallable(() -> movieLocalDAO.hasMovie(movie.getId()))
                .subscribeOn(Schedulers.newThread()).blockingFirst();
    }

    @Override
    public Observable<MovieDetails> fetchMovieDetails(long id) {
        return serverApi.fetchMovieDetails(id, BuildConfig.API_KEY, Locale.getDefault().getLanguage());
    }
}
