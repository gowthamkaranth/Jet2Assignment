package com.jet2.Jet2Assignment.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.repositories.BlogPostRepository

class BlogPostViewModel(val app :Application) : AndroidViewModel(app){
    //TODO use dependency injection
    private val repository = BlogPostRepository(app)

     fun getBlogs():LiveData<PagedList<BlogResponseEntity>>{
         return repository.getBlogResponse()
     }
}