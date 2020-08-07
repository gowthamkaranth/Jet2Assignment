package com.jet2.Jet2Assignment.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jet2.Jet2Assignment.R
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity

class BlogListViewAdapter(val context: Context): PagedListAdapter<BlogResponseEntity, BlogListViewAdapter.BlogListViewHolder>(Callback)
{


    class BlogListViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        var blogText : TextView
init {
      blogText =  itemView.findViewById(R.id.tv_title);
}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogListViewHolder {
        val inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_list_item, parent, false)
        return BlogListViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: BlogListViewHolder, position: Int) {
        val blogData = getItem(position)
        if (blogData != null) {
            holder.blogText.text = blogData.articleTitle
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