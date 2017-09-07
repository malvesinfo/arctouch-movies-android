package com.arctouch.movies.movie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class BelongsToCollection {

    private long id;

    private String name;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    public BelongsToCollection(long id, String name, String backdropPath, String posterPath) {
        this.id = id;
        this.name = name;
        this.backdropPath = backdropPath;
        this.posterPath = posterPath;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
