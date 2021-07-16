package com.example.mvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel  = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())
    }

    fun onNext(view: View) {
        mainViewModel.nextQuote()
        setQuote(mainViewModel.getQuote())
    }
    fun onPrevious(view: View) {

        mainViewModel.previousQuote()
        setQuote(mainViewModel.getQuote())
    }
    fun onShare(view: View) {
        val intent  = Intent(Intent(Intent.ACTION_SEND))
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text+"    https://rahulyadav8650.wordpress.com/")
        startActivity(intent)
    }
    fun setQuote(quote: Quote){
        quoteText.text = quote.text
        quoteAuthor.text = quote.author
    }

}