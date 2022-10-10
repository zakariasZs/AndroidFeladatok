package com.example.lab2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dict: IDictionary = ListDictionary
        println("Number of words: ${dict.size()}")
        var word: String?
        while(true){
            print("What to find? ")
            word = readLine()
            if( word.equals("quit")){
                break
            }
            println("Result: ${word?.let { dict.find(it) }}")
        }

    }
}