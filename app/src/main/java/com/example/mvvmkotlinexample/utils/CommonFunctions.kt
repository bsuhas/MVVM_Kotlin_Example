package com.example.mvvmkotlinexample.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CommonFunctions {
    var ISSUE_DATA = "ISSUE_DATA"

    @SuppressLint("SimpleDateFormat")
    fun formattedDate(updatedAt: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val output = SimpleDateFormat("MM-dd-yyyy")

        var d: Date? = Date()
        try {
            d = input.parse(updatedAt)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return output.format(d)
    }
}