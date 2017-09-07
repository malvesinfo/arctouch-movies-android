package com.arctouch.movies.util;

import com.arctouch.movies.movie.model.Genre;

import java.util.List;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class StringUtils {
    public static String getMovieGenres(List<Genre> genres) {
        StringBuilder strGenres = new StringBuilder();
        if (genres != null && !genres.isEmpty()) {
            for (int i = 0; i < genres.size(); i++) {
                strGenres.append(genres.get(i).getName()).append(i != genres.size() - 1 ? ", " : "");
            }
        }

        return strGenres.toString();
    }
}
