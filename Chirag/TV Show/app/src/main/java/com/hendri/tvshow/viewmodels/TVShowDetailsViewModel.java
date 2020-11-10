package com.hendri.tvshow.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.hendri.tvshow.database.TVShowsDatabase;
import com.hendri.tvshow.models.TVShow;
import com.hendri.tvshow.repositories.TVShowDetailsRepository;
import com.hendri.tvshow.responses.TVShowDetailsResponse;

import io.reactivex.Completable;

public class TVShowDetailsViewModel extends AndroidViewModel {

    private TVShowDetailsRepository tvShowDetailsRepository;
    private TVShowsDatabase tvShowsDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowsDatabase = TVShowsDatabase.getTVShowDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }

    public Completable addToWatchList(TVShow tvShow) {
        return tvShowsDatabase.tvShowDao().addToWatchList(tvShow);
    }
}
