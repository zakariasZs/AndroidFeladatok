package com.example.lab2
import com.example.lab2.IDictionary.Companion.DICTIONARY_NAME

import java.io.File

object ListDictionary : IDictionary{

    private val words = mutableListOf<String>()

    init{
        File(DICTIONARY_NAME).forEachLine{add(it)}
        println("File reading finished!")
    }

    override fun add(word: String)= words.add(word)

    override fun find(word: String) = words.contains(word)

    override fun size() = words.size
}