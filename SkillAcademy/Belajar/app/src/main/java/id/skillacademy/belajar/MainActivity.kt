package id.skillacademy.belajar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.btn_click)
        button.setOnClickListener { Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show() }

        val rootLayout = findViewById<LinearLayout>(R.id.root_layout)
        val btn_snack = findViewById<Button>(R.id.btn_snack)
        btn_snack.setOnClickListener { Snackbar.make(rootLayout, "From Snack", Snackbar.LENGTH_SHORT).show() }
    }
}
