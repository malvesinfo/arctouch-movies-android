package com.arctouch.movies.movie.model;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class ProductionCompany {

    private long id;

    private String name;

    public ProductionCompany(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
