package id.skillacademy.themovie

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeDataSource {

    @GET("discover/movie")
    fun discoverMovie(
        @Query("api_key")
        apiKey: String = BuildConfig.TMDB_API_KEY
    ): Call<HomeResponse>
}