package com.luisotinianodavila.movieclean.domain.model;

public class Movie {
    private String title;
    private String backdropPath;
    private int movieId;

    private String posterPath;
    private Double voteAverage;
    private Double popularity;
    private Integer vote_count;
    private String overview;
    private String releaseDate;

    public Movie(int movieId, String title, Double voteAverage, String releaseDate, String overview, String posterPath) {
        this.movieId = movieId;
        this.title = title;
        this.voteAverage = voteAverage;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterPath = posterPath;
    }

    public Movie(int movieId, String title, Double popularity, Double voteAverage, Integer vote_count, String releaseDate, String overview, String backdropPath) {
        this.movieId = movieId;
        this.title = title;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.vote_count = vote_count;
        this.releaseDate =releaseDate;
        this.overview = overview;
        this.backdropPath = backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
