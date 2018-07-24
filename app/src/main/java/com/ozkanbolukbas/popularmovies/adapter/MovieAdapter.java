package com.ozkanbolukbas.popularmovies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ozkanbolukbas.popularmovies.R;
import com.ozkanbolukbas.popularmovies.model.Movie;
import com.ozkanbolukbas.popularmovies.utils.MovieNetworkUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Özkan Bölükbaş on 18.06.2018
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movieList;
    private ItemClickListener itemClickListener;

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public ItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MovieAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Movie getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public int getItemCount() {
        if (movieList == null) {
            return 0;
        }
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        ImageView moviePoster;
        TextView movieTitle;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            moviePoster = itemView.findViewById(R.id.list_movie_poster);
            movieTitle = itemView.findViewById(R.id.list_movie_title);
        }

        public void bind(final Movie movie) {
            String movieImageUrl = movie.getmPosterPath();
            String movieTitleText = movie.getmTitle();
            movieTitle.setText(movieTitleText);
            Picasso.get().load(MovieNetworkUtils.MOVIE_POSTER_URL + movieImageUrl).into(moviePoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getItemClickListener().onItemClick(movie);
                }
            });
        }

    }

    public interface ItemClickListener {
        void onItemClick(Movie clickedMovie);
    }
}
