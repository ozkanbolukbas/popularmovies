package com.ozkanbolukbas.popularmovies;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.ozkanbolukbas.popularmovies.adapter.MovieAdapter;
import com.ozkanbolukbas.popularmovies.model.Movie;
import com.ozkanbolukbas.popularmovies.utils.MovieJsonUtils;
import com.ozkanbolukbas.popularmovies.utils.MovieNetworkUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.ItemClickListener {

    private static String LOG_TAG = MainActivity.class.getSimpleName();
    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movie_rv);
        if (getResources().getConfiguration().orientation == 1) {
            gridLayoutManager = new GridLayoutManager(this, 2);
        } else {
            gridLayoutManager = new GridLayoutManager(this, 3);

        }
        movies = new ArrayList<>();
        adapter = new MovieAdapter();
        adapter.setItemClickListener(this);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(adapter);

        showMovies(MovieNetworkUtils.POPULAR);
    }

    private void showMovies(String sort) {
        new FetchMovie().execute(sort);
    }

    @Override
    public void onItemClick(Movie clickedMovie) {
        navigateToDetailsActivity(clickedMovie);
    }

    private void navigateToDetailsActivity(Movie movie) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.INTENT_MOVIE, movie);
        startActivity(intent);
    }

    private class FetchMovie extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected List<Movie> doInBackground(String... params) {
            URL url = MovieNetworkUtils.buildUrl(params[0]);
            String movieData;
            try {
                movieData = MovieNetworkUtils.getResponseFromHttpUrl(url);
                movies = MovieJsonUtils.parseMovieJson(movieData);
                return movies;
            } catch (IOException e) {
                Log.e(LOG_TAG, "doInBackground: " + e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);
            adapter.setMovieList(movies);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            showMovies(MovieNetworkUtils.POPULAR);
            setTitle(getString(R.string.popular_movies));
        }
        if (id == R.id.action_top_rated) {
            showMovies(MovieNetworkUtils.TOP_RATED);
            setTitle(getString(R.string.top_rated_movies));
        }
        return super.onOptionsItemSelected(item);
    }
}
