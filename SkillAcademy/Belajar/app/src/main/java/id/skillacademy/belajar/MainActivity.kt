package id.skillacademy.belajar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_click)
        button.setOnClickListener { startActivity(Intent(this, DetailActivity::class.java)) }

        val rootLayout = findViewById<LinearLayout>(R.id.root_layout)
        val btnSnack = findViewById<Button>(R.id.btn_snack)
        btnSnack.setOnClickListener { Snackbar.make(rootLayout, "From Snack", Snackbar.LENGTH_SHORT).show() }

        val tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.setText("onCreate State Activity")

        supportFragmentManager.beginTransaction().replace(R.id.fl_main, MainFragment()).commit()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
