package com.jet2.Jet2Assignment.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity

@Dao
interface BlogResponseDAO {
    @Insert
    fun insert(blogResponseEntity: BlogResponseEntity)

    @Insert
    fun insertAll(blogResponseList : List<BlogResponseEntity>)

    @Query("SELECT * FROM blog_response_entity" )
    fun sendDataSource(): DataSource.Factory<Int, BlogResponseEntity>

}