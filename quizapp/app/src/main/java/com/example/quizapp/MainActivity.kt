package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val TAG: String = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById<Button>(R.id.button)

//        startButton.setOnClickListener{
//            Log.d(TAG, "Button clicked")
//            val textField: EditText = findViewById(R.id.editTextTextPersonName)
//            val textFieldText = textField.text
//            Log.d(TAG, textFieldText.toString())
//
//            val toast = Toast.makeText(applicationContext, textFieldText.toString(), Toast.LENGTH_SHORT)
//            toast.show()
//        }

        startButton.setOnClickListener{
            val textField: EditText = findViewById(R.id.editTextTextPersonName)
            val textFieldText = textField.text
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("user_name", textFieldText)
            startActivity(intent)
        }

        val contentButton : Button = findViewById<Button>(R.id.contact_button)

    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart() function called")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop() function called")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause() function called")
    }
}