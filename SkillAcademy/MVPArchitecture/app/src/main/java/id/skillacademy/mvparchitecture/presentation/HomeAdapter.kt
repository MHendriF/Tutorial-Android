package id.skillacademy.mvparchitecture.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.skillacademy.mvparchitecture.R
import id.skillacademy.mvparchitecture.data.Result

class HomeAdapter(private val results: List<Result>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.item_home,
                    parent,
                    false
                )
        )
    }

    override fun getItemCount(): Int {
        return results.count()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(results[holder.adapterPosition])
    }

    inner class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(results: Result) {
            with(itemView) {
                val title = findViewById<TextView>(R.id.tv_title)
                title.text = results.title

                val overview = findViewById<TextView>(R.id.tv_overview)
                overview.text = results.overview
            }
        }
    }
}