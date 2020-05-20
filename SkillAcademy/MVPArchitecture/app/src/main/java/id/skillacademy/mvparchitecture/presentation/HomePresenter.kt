package id.skillacademy.mvparchitecture.presentation

import id.skillacademy.mvparchitecture.data.HomeDataSource
import id.skillacademy.mvparchitecture.data.HomeResponse
import retrofit2.Call
import retrofit2.Callback

class HomePresenter(private val view: HomeView, private val dataSource: HomeDataSource) {

    fun discoverMovie() {
        view.onShowLoading()

        dataSource.discoverMovie().enqueue(object : Callback<HomeResponse> {
            override fun onResponse(
                call: Call<HomeResponse>,
                response: retrofit2.Response<HomeResponse>
            ) {
                view.onHideLoading()
                view.onResponse(response.body()?.result ?: emptyList())
            }

            override fun onFailure(call: Call<HomeResponse>, t: Throwable) {
                view.onHideLoading()
                view.onFailure(t)
            }

        })
    }
}