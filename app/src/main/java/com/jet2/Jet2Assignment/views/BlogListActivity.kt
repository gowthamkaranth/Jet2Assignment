package com.jet2.Jet2Assignment.views

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jet2.Jet2Assignment.R
import com.jet2.Jet2Assignment.viewmodels.BlogPostViewModel
import com.jet2.Jet2Assignment.views.adapter.BlogListViewAdapter

class BlogListActivity : AppCompatActivity(){
    lateinit var blogPostViewModel: BlogPostViewModel
    lateinit var blogRecyclerView : RecyclerView
    lateinit var blogListViewAdapter :BlogListViewAdapter
    lateinit var loaderLayout : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blog_list)
        init()
        initListeners()
    }
    private fun init(){
        blogRecyclerView = findViewById(R.id.timeline_recycler_view)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        blogRecyclerView.layoutManager = layoutManager
        blogListViewAdapter = BlogListViewAdapter(this)
        blogRecyclerView.setAdapter(blogListViewAdapter)
        blogPostViewModel = ViewModelProvider(this).get(BlogPostViewModel::class.java)
        loaderLayout = findViewById(R.id.ll_progress)
        loaderLayout.visibility = View.VISIBLE
    }

    private fun initListeners(){
        blogPostViewModel.getBlogs().observe(this, Observer {
        blogListViewAdapter.submitList(it)
            loaderLayout.visibility = View.GONE
        })
    }
}