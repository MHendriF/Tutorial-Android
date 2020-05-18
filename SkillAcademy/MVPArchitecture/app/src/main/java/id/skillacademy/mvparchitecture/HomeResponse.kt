package id.skillacademy.mvparchitecture

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