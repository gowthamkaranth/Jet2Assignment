package com.jet2.Jet2Assignment.views.adapter

import android.content.Context
import android.util.Log
import androidx.paging.PagedList
import com.jet2.Jet2Assignment.models.BlogResponseDTO
import com.jet2.Jet2Assignment.repositories.BlogPostRepository
import com.jet2.Jet2Assignment.blogApiService
import com.jet2.Jet2Assignment.database.BlogDatabase
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.disposable
import com.jet2.Jet2Assignment.utils.DATABASE_PAGE_SIZE
import com.jet2.Jet2Assignment.utils.convertResponseToEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BlogRepoBoundaryCallBack (val blogPostRepository: BlogPostRepository,val context: Context) : PagedList.BoundaryCallback<BlogResponseEntity>()
{
    val TAG = BlogRepoBoundaryCallBack::class.java.name
    var page = 1
    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        page = 1
        getBlogPosts(page, DATABASE_PAGE_SIZE)
    }

    override fun onItemAtEndLoaded(itemAtEnd: BlogResponseEntity) {
        if(itemAtEnd.pagenumber!=null) {
        page = itemAtEnd.pagenumber!! + 1
            getBlogPosts(page, DATABASE_PAGE_SIZE)
        }

    }

    override fun onItemAtFrontLoaded(itemAtFront: BlogResponseEntity) {
    }
    /**
     * Fetch Blog items through network call
     * @param page page number
     * @param limit items per page
     */
    fun getBlogPosts(page:Int,limit:Int) {
        disposable = blogApiService.fetchBlogPost(page.toString(),limit.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                    result ->
                    val blogResponseEntities = convertResponseToEntity(result,page)
                        updateDataToDB(blogResponseEntities)

            },
                {
                        error ->
                    Log.d(TAG,error.message)
                }
            )

    }

    fun updateDataToDB(blogEntity : List<BlogResponseEntity>){
        Thread(Runnable {
            updateBlogDB(blogEntity)
        }).start()
    }
    fun updateBlogDB(blogEntity: List<BlogResponseEntity>){
    BlogDatabase.getInstance(context)?.blogResponseDAO?.insertAll(blogEntity)
    }

}