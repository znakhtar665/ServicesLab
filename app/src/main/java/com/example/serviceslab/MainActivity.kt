package com.example.serviceslab

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val startButton = findViewById<Button>(R.id.startButton)


        startButton.setOnClickListener {
            val countdownTime = editTextNumber.text.toString().toIntOrNull()
            if (countdownTime != null && countdownTime > 0) {
                val intent = Intent(this, CountdownService::class.java).apply {
                    putExtra("countdownTime", countdownTime)
                }
                startService(intent)
            }
        }
    }
}