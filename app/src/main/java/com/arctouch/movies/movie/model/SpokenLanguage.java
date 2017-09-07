package com.arctouch.movies.movie.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class SpokenLanguage {

    @SerializedName("name")
    private String name;

    @SerializedName("iso_639_1")
    private String iso6391;

    public SpokenLanguage(String name, String iso6391) {
        this.name = name;
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public String getIso6391() {
        return iso6391;
    }
}
