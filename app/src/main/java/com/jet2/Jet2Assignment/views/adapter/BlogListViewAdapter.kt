package com.jet2.Jet2Assignment.views.adapter

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jet2.Jet2Assignment.R
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.utils.compareDate
import java.text.SimpleDateFormat
import java.util.*

class BlogListViewAdapter(val context: Context): PagedListAdapter<BlogResponseEntity, BlogListViewAdapter.BlogListViewHolder>(Callback)
{


    class BlogListViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        var articleContent : TextView
        var userNameTextView : TextView
        var userDesignationTextView :TextView
        var updatedTimeTextView : TextView
        var articleTitleTextView : TextView
        var articleTitleUrlTextView : TextView
        var likesTextView : TextView
        var commentsTextView : TextView
        var userAvatarImageView :ImageView
        var articleImageView : ImageView
init {
    articleContent =  itemView.findViewById(R.id.tv_article_content)
    userNameTextView = itemView.findViewById(R.id.tv_user_name)
    userDesignationTextView = itemView.findViewById(R.id.tv_user_designation)
    updatedTimeTextView = itemView.findViewById(R.id.tv_updated_time)
    articleTitleTextView = itemView.findViewById(R.id.tv_article_title)
    articleTitleUrlTextView = itemView.findViewById(R.id.tv_article_url)
    likesTextView = itemView.findViewById(R.id.tv_likes)
    commentsTextView =  itemView.findViewById(R.id.tv_comments)
    userAvatarImageView = itemView.findViewById(R.id.iv_user_avatar)
    articleImageView = itemView.findViewById(R.id.iv_blog_image)
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.timeline_list_item, parent, false)
        return BlogListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BlogListViewHolder, position: Int) {
        val blogData = getItem(position)
        if (blogData != null) {
            holder.userNameTextView.text = blogData.userName
            holder.userDesignationTextView.text = blogData.userDesignation
            if(blogData.articleTitle!=null) {
                holder.articleTitleTextView.visibility = View.VISIBLE
                holder.articleTitleTextView.text = blogData.articleTitle
            }else{
                holder.articleTitleTextView.visibility = View.GONE
            }
            if(blogData.articleURL!=null) {
                holder.articleTitleUrlTextView.visibility =View.VISIBLE
                holder.articleTitleUrlTextView.text = Html.fromHtml(blogData.articleURL,Html.FROM_HTML_MODE_COMPACT)
            }else{
                holder.articleTitleUrlTextView.visibility =View.GONE
            }
            holder.articleContent.text = blogData.articleContent
            holder.likesTextView.text = "${blogData.likes} Likes"
            holder.commentsTextView.text = "${blogData.comments} Comments"

            if(blogData.publishedTime!=null) {
                val days = compareDate(blogData.publishedTime!!)
                holder.updatedTimeTextView.text = "$days Days"
            } else{
                holder.updatedTimeTextView.text = ""
            }
            if(blogData.userAvatar!=null){

                Glide.with(context)
                    .load(blogData.userAvatar).into(holder.userAvatarImageView)
            }
            else{
                Glide.with(context).clear(holder.userAvatarImageView);
                holder.userAvatarImageView.setImageDrawable(null)
            }
            if(blogData.articleImage != null && blogData.articleImage!!.isNotEmpty()){
                holder.articleImageView.visibility = View.VISIBLE
                Glide.with(context)
                    .load(blogData.articleImage).into(holder.articleImageView)
                }else{
                Glide.with(context).clear(holder.articleImageView)
                holder.articleImageView.setImageDrawable(null)
                holder.articleImageView.visibility = View.GONE
            }
        }

    }

    companion object Callback : DiffUtil.ItemCallback<BlogResponseEntity>(){
        override fun areItemsTheSame(oldItem: BlogResponseEntity, newItem: BlogResponseEntity): Boolean {
           return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BlogResponseEntity, newItem: BlogResponseEntity): Boolean {
           return oldItem.publishedTime == newItem.publishedTime
        }

    }
}