package com.arctouch.movies.serverapi.response;

import com.arctouch.movies.movie.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieResponse {

    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private List<Movie> movieList;

    public MovieResponse(int page, int totalResults, int totalPages, List<Movie> movieList) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.movieList = movieList;
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
