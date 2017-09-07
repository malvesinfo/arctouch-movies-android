package com.arctouch.movies.movie.dao.entity;

import android.support.annotation.NonNull;

import com.arctouch.movies.movie.model.Movie;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieEntity extends RealmObject {

    @PrimaryKey
    private long id;

    private boolean adult;

    private boolean video;

    private double popularity;

    private String title;

    private String overview;

    private String backdropPath;

    private String originalLanguage;

    private String originalTitle;

    private String posterPath;

    private String releaseDate;

    private float voteAverage;

    private long voteCount;

    public MovieEntity() {
    }

    public MovieEntity(long id, boolean adult, String backdropPath, String originalLanguage,
                       String originalTitle, String overview, double popularity,
                       String posterPath, String releaseDate, String title, boolean video, float voteAverage,
                       long voteCount) {
        this.id = id;
        this.adult = adult;
        this.backdropPath = backdropPath;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.title = title;
        this.video = video;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public boolean isVideo() {
        return video;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    public static List<MovieEntity> toEntity(@NonNull List<Movie> movieList) {
        List<MovieEntity> movieEntities = new ArrayList<>();
        for (Movie movie : movieList) {
            movieEntities.add(MovieEntity.toEntity(movie));
        }

        return movieEntities;
    }

    public static MovieEntity toEntity(@NonNull Movie movie) {
        return new MovieEntity(movie.getId(), movie.isAdult(), movie.getBackdropPath(), movie.getOriginalLanguage(),
                movie.getOriginalTitle(), movie.getOverview(), movie.getPopularity(), movie.getPosterPath(),
                movie.getReleaseDate(), movie.getTitle(), movie.isVideo(), movie.getVoteAverage(), movie.getVoteCount());
    }

    public Movie toModel() {
        return new Movie(id, adult, backdropPath, originalLanguage, originalTitle, overview, popularity,
                posterPath, releaseDate, title, video, voteAverage, voteCount);
    }

}
