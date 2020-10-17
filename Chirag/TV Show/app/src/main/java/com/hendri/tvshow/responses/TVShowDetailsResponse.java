package com.hendri.tvshow.responses;

import com.google.gson.annotations.SerializedName;
import com.hendri.tvshow.models.TVShowDetails;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
