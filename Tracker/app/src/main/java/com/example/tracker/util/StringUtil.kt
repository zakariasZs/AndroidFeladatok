package com.example.tracker.util

import java.text.SimpleDateFormat
import java.util.*

class StringUtil {

    companion object{

        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat("yyyy.MM.dd")
            return format.format(date)
        }



    }



}