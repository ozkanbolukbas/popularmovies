package com.ozkanbolukbas.popularmovies.utils;

import android.util.Log;

import com.ozkanbolukbas.popularmovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Özkan Bölükbaş on 18.06.2018
 */

public class MovieJsonUtils {
    private static final String LOG_TAG = MovieJsonUtils.class.getSimpleName();

    private static final String RESULTS = "results";
    private static final String VOTE_AVARAGE = "vote_average";
    private static final String POSTER_PATH = "poster_path";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";

    public static List<Movie> parseMovieJson(String json) {
        try {
            JSONObject baseJsonObject = new JSONObject(json);
            JSONArray result = baseJsonObject.getJSONArray(RESULTS);
            List<Movie> movieList = new ArrayList<>();
            for (int i = 0; i < result.length(); i++) {
                JSONObject movieObject = result.getJSONObject(i);
                Movie movie = new Movie();
                movie.setmVote(movieObject.getDouble(VOTE_AVARAGE));
                movie.setmPosterPath(movieObject.optString(POSTER_PATH));
                movie.setmTitle(movieObject.optString(ORIGINAL_TITLE));
                movie.setmOverview(movieObject.optString(OVERVIEW));
                movie.setmReleaseDate(movieObject.optString(RELEASE_DATE));
                movieList.add(movie);
            }
            return movieList;

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error parsing JSONObject", e);
            return null;
        }

    }

}
