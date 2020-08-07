package com.jet2.Jet2Assignment.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jet2.Jet2Assignment.blogApiService
import com.jet2.Jet2Assignment.database.BlogDatabase
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.disposable
import com.jet2.Jet2Assignment.utils.DATABASE_PAGE_SIZE
import com.jet2.Jet2Assignment.views.adapter.BlogRepoBoundaryCallBack
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogPostRepository(val context: Context) {
    val TAG = BlogPostRepository::class.java.name

    fun getBlogResponse() : LiveData<PagedList<BlogResponseEntity>>
    {
        val livePagedListBuilder = LivePagedListBuilder<Int,BlogResponseEntity>(getBlogFromDB(),DATABASE_PAGE_SIZE)
        return livePagedListBuilder.setBoundaryCallback(BlogRepoBoundaryCallBack(this,context)).build()
    }
    /**
     * Fetch Blog items through network call
     * @param page page number
     * @param limit items per page
     */
    fun getBlogPosts(page:String,limit:String) {
        disposable = blogApiService.fetchBlogPost(page,limit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                result ->
            },
                {
                    error ->
                    Log.d(TAG,error.message)
                }
            )

    }
    fun getBlogFromDB(): DataSource.Factory<Int, BlogResponseEntity> {
            return  BlogDatabase.getInstance(context)?.blogResponseDAO?.sendDataSource()!!
    }

}