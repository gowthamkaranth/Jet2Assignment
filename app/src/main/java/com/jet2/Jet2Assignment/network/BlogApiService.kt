package com.jet2.Jet2Assignment

import com.jet2.Jet2Assignment.models.BlogResponseDTO
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

val blogApiService by lazy {
    BlogApiService.create()
}
var disposable: Disposable? = null


interface BlogApiService {
    //TODO Remove hardcoded values
    @GET("blogs")
    fun fetchBlogPost(@Query("page")page:String,@Query("limit")limit:String) : Observable<List<BlogResponseDTO>>

    companion object{
        fun create() : BlogApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(BuildConfig.JET2_CLOUD_URL)
                .build()
            return retrofit.create(BlogApiService::class.java)
        }
    }
}