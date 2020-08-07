package com.jet2.Jet2Assignment.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_response_entity")
data class BlogResponseEntity(@PrimaryKey(autoGenerate = true)var id :Int? = null, var userName:String? = null,
                              var userDesignation:String? = null, var publishedTime:String? = null, var articleTitle:String? = null,
                              var articleContent:String? = null, var articleImage:String? = null,
                              var articleURL:String? = null, var likes:Int? = null,
                              var comments:Int? = null, var userAvatar:String? = null, var pagenumber:Int? = null)