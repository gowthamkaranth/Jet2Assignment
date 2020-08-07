package com.jet2.Jet2Assignment.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.jet2.Jet2Assignment.R
import com.jet2.Jet2Assignment.Repositories.BlogPostRepository
import com.jet2.Jet2Assignment.blogApiService
import com.jet2.Jet2Assignment.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}