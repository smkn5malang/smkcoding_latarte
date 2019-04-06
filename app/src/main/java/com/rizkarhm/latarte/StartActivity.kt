package com.rizkarhm.latarte

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val getStart = findViewById<Button>(R.id.button_start) as Button
        getStart.setOnClickListener{
            val intent = Intent(this, ProfilActivity::class.java )
            startActivity(intent)
        }
    }
}
