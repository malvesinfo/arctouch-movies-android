package com.arctouch.movies.serverapi;

import android.support.annotation.NonNull;

import com.arctouch.movies.movie.model.MovieDetails;
import com.arctouch.movies.serverapi.response.MovieResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public interface AppServerApi {

    @GET("search/movie")
    Observable<MovieResponse> fetchMovies(@Query("api_key") @NonNull String apiKey, @Query("language") @NonNull String language,
                                          @Query("query") @NonNull String query, @Query("page") int page,
                                          @Query("include_adult") boolean adult);

    @GET("movie/{movie_id}")
    Observable<MovieDetails> fetchMovieDetails(@Path("movie_id") long id, @Query("api_key") @NonNull String apiKey,
                                               @Query("language") @NonNull String language);

    @GET("movie/popular")
    Observable<MovieResponse> fetchPopularMovies(@Query("api_key") @NonNull String apiKey,
                                                 @Query("language") @NonNull String language,
                                                 @Query("page") int page);


}
