package id.skillacademy.mvparchitecture.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import id.skillacademy.mvparchitecture.R
import id.skillacademy.mvparchitecture.data.Result
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.discoverMovie()
    }

    override fun onShowLoading() {
        pb_home.visibility = View.VISIBLE
    }

    override fun onHideLoading() {
        pb_home.visibility = View.GONE
        rv_home.visibility = View.VISIBLE
    }

    override fun onResponse(results: List<Result>) {
        rv_home.addItemDecoration(DividerItemDecoration(this@HomeActivity, DividerItemDecoration.VERTICAL))
        rv_home.adapter = HomeAdapter(results)
    }

    override fun onFailure(error: Throwable) {
        Log.e(HomeActivity::class.java.simpleName, "${error.printStackTrace()}")
    }
}
