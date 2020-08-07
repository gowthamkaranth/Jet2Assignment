package com.jet2.Jet2Assignment.utils

import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.models.BlogResponseDTO

fun convertResponseToEntity(response : List<BlogResponseDTO>, page: Int):List<BlogResponseEntity>{
    val blogEntities  = mutableListOf<BlogResponseEntity>()
    for(blogItem in response){
        val blogEntity = BlogResponseEntity()
        blogItem.user.let { users ->
            if(users.isNotEmpty()) {
                users[0].let { user ->
                    blogEntity.userName = user.name
                    blogEntity.userAvatar = user.avatar
                    blogEntity.userDesignation = user.designation

                }
            }
        }
        blogItem.media.let { mediaList ->
            if(mediaList.isNotEmpty()) {
                mediaList[0].let { media ->
                    blogEntity.articleTitle = media.title
                    blogEntity.articleImage = media.image
                    blogEntity.articleURL = media.url
                    blogEntity.publishedTime = media.createdAt
                }
            }
        }
        blogItem.comments.let {
            blogEntity.comments = it
        }
        blogItem.content.let {
            blogEntity.articleContent = it
        }
        blogItem.likes.let {
            blogEntity.likes = it
        }
        blogEntity.pagenumber  = page
        blogEntities.add(blogEntity)
    }
    return blogEntities
}