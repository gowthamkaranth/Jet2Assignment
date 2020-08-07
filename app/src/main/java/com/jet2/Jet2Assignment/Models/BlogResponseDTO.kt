package com.jet2.Jet2Assignment.Models

import com.google.gson.annotations.SerializedName

data class BlogResponseDTO(@SerializedName("id") val id : Int,
                           @SerializedName("createdAt") val createdAt : String,
                           @SerializedName("content") val content : String,
                           @SerializedName("comments") val comments : Int,
                           @SerializedName("likes") val likes : Int,
                           @SerializedName("media") val media : List<Media>,
                           @SerializedName("user") val user : List<User>)
data class Media(@SerializedName("id") val id : Int,
                 @SerializedName("blogId") val blogId : Int,
                 @SerializedName("createdAt") val createdAt : String,
                 @SerializedName("image") val image : String,
                 @SerializedName("title") val title : String,
                 @SerializedName("url") val url : String
)
data class User(@SerializedName("id") val id : Int,
                @SerializedName("blogId") val blogId : Int,
                @SerializedName("createdAt") val createdAt : String,
                @SerializedName("name") val name : String,
                @SerializedName("avatar") val avatar : String,
                @SerializedName("lastname") val lastname : String,
                @SerializedName("city") val city : String,
                @SerializedName("designation") val designation : String,
                @SerializedName("about") val about : String)