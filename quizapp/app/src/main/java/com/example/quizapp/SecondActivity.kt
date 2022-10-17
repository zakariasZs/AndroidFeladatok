package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        init()
    }

    private fun init(){
        val bundle: Bundle? =intent.extras

        val message = bundle?.get("user_name").toString()

        val nameTextView = findViewById<TextView>(R.id.player_name)
        nameTextView.text = message
    }
}