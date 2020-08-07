package com.jet2.Jet2Assignment.Repositories

import android.util.Log
import com.jet2.Jet2Assignment.blogApiService
import com.jet2.Jet2Assignment.disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogPostRepository {
    val TAG = BlogPostRepository::class.java.name

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
                result -> Log.d(TAG,result.toString())
            },
                {
                    error ->
                    Log.d(TAG,error.message)
                }
            )

    }
}