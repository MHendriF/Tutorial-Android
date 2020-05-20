package id.skillacademy.mvparchitecture.data

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("results")
    val result: List<Result>
)

data class Result(
    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String
)