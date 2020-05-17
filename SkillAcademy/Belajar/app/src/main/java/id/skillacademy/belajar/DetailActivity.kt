package id.skillacademy.belajar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val itemAdapter = findViewById<RecyclerView>(R.id.rv_detail)
        itemAdapter.adapter = DetailAdapter()

        val button = findViewById<Button>(R.id.btn_custom_view)
        button.setOnClickListener { startActivity(Intent(this, CustomActivity::class.java)) }
    }
}
