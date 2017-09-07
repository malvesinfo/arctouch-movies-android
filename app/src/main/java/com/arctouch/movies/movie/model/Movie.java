package com.arctouch.movies.movie.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class Movie implements Serializable {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA = "EXTRA_MOVIE";

    private long id;

    private boolean adult;

    private boolean video;

    private double popularity;

    private String title;

    private String overview;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("vote_count")
    private long voteCount;

    public Movie(long id, boolean adult, String backdropPath, String originalLanguage,
                 String originalTitle, String overview, double popularity, String posterPath,
                 String releaseDate, String title, boolean video, float voteAverage, long voteCount) {
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

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }

    /**
     * @return @{@link Calendar} Year!
     */
    public int getMovieYear() {
        int result = 0;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(simpleDateFormat.parse(getReleaseDate()).getTime());

            result = calendar.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
}
