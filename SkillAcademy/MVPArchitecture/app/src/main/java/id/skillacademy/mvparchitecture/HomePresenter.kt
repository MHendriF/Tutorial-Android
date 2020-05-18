package id.skillacademy.mvparchitecture

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback

class HomePresenter(private val view: HomeView) {

    fun discoverMovie() {
        view.onShowLoading()

        val dataSource = NetworkProvider.providesHttpAdapter().create(HomeDataSource::class.java)
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