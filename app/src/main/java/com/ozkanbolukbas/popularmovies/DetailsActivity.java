package com.ozkanbolukbas.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ozkanbolukbas.popularmovies.model.Movie;
import com.ozkanbolukbas.popularmovies.utils.MovieNetworkUtils;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    public static final String INTENT_MOVIE = "movie";
    private Movie movie;
    ImageView detailsImage;
    TextView detailsOverview;
    TextView detailsTitle;
    TextView detailsDate;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        detailsImage = findViewById(R.id.details_image);
        detailsOverview = findViewById(R.id.movie_details);
        detailsTitle = findViewById(R.id.details_title);
        detailsDate = findViewById(R.id.details_date);
        ratingBar = findViewById(R.id.ratingBar);

        movie = (Movie) getIntent().getSerializableExtra(INTENT_MOVIE);
        if (movie != null) {
            setTitle(movie.getmTitle());
            Picasso.get().load(MovieNetworkUtils.MOVIE_POSTER_URL + movie.getmPosterPath()).into(detailsImage);
            detailsOverview.setText(movie.getmOverview());
            detailsTitle.setText(movie.getmTitle());
            detailsDate.setText(movie.getmReleaseDate());
            ratingBar.setRating((float) (movie.getmVote() / 2.0));
        }
    }
}
