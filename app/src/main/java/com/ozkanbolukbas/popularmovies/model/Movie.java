package com.ozkanbolukbas.popularmovies.model;

import java.io.Serializable;

/**
 * Created by Özkan Bölükbaş on 18.06.2018
 */

public class Movie implements Serializable {
    private double mVote;
    private String mPosterPath;
    private String mTitle;
    private String mOverview;
    private String mReleaseDate;

    public Movie() {

    }

    public double getmVote() {
        return mVote;
    }

    public void setmVote(double mVote) {
        this.mVote = mVote;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
