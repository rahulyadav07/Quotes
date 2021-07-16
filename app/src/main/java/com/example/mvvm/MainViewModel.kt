package com.example.mvvm

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainViewModel(var context: Context) : ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuotefromassest()
    }

    private fun loadQuotefromassest(): Array<Quote> {
        val inputstring = context.assets.open("assest")
        val size: Int = inputstring.available()
        val buffer = ByteArray(size)
        inputstring.read(buffer)
        inputstring.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote() = quoteList[index]


    fun nextQuote() {

        quoteList[++index]

    }

    fun previousQuote() {
        if (index != 0) {
            quoteList[--index]
        } else {
            Toast.makeText(context, "you can not  click on previous", Toast.LENGTH_SHORT).show()
        }
    }
}