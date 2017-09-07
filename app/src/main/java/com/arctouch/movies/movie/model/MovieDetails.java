package com.arctouch.movies.movie.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class MovieDetails {

    private long id;

    private boolean adult;

    private long budget;

    private long revenue;

    private long runtime;

    private boolean video;

    private String homepage;

    private String status;

    private String tagline;

    private String title;

    private List<Genre> genres;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("belongs_to_collection")
    private BelongsToCollection belongsToCollection;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("overview")
    private String overview;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private long voteCount;

    public MovieDetails(long id, boolean adult, long budget, long revenue, long runtime,
                        boolean video, String homepage, String status, String tagline,
                        String title, List<Genre> genres, String backdropPath, BelongsToCollection belongsToCollection,
                        String imdbId, String originalLanguage, String originalTitle, String overview,
                        double popularity, String posterPath, List<ProductionCompany> productionCompanies,
                        List<ProductionCountry> productionCountries, String releaseDate, List<SpokenLanguage> spokenLanguages,
                        double voteAverage, long voteCount) {
        this.id = id;
        this.adult = adult;
        this.budget = budget;
        this.revenue = revenue;
        this.runtime = runtime;
        this.video = video;
        this.homepage = homepage;
        this.status = status;
        this.tagline = tagline;
        this.title = title;
        this.genres = genres;
        this.backdropPath = backdropPath;
        this.belongsToCollection = belongsToCollection;
        this.imdbId = imdbId;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.spokenLanguages = spokenLanguages;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public long getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public long getBudget() {
        return budget;
    }

    public long getRevenue() {
        return revenue;
    }

    public long getRuntime() {
        return runtime;
    }

    public boolean isVideo() {
        return video;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public BelongsToCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public String getImdbId() {
        return imdbId;
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

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public long getVoteCount() {
        return voteCount;
    }
}
