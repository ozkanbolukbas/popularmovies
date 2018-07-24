package com.ozkanbolukbas.popularmovies.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Özkan Bölükbaş on 18.06.2018
 */

public class MovieNetworkUtils {
    private static final String LOG_TAG = MovieNetworkUtils.class.getSimpleName();

    private static final String MOVIE_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String MOVIE_QUERY_API = "api_key";
    private static final String API_KEY = "cd64c3c98ab6a38c26059418bd33357c";
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";
    public static final String MOVIE_POSTER_URL = "http://image.tmdb.org/t/p/w185";

    final

    public static URL buildUrl(String movieQuery) {
        Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
                .appendPath(movieQuery)
                .appendQueryParameter(MOVIE_QUERY_API, API_KEY)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error creating Url", e);
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
