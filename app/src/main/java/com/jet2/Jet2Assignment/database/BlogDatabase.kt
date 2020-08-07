package com.jet2.Jet2Assignment.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jet2.Jet2Assignment.database.dao.BlogResponseDAO
import com.jet2.Jet2Assignment.database.entities.BlogResponseEntity
import com.jet2.Jet2Assignment.utils.DATABASE_NAME

@Database(entities = [BlogResponseEntity::class], version = 1, exportSchema = false)
abstract class BlogDatabase :RoomDatabase(){

    abstract val blogResponseDAO : BlogResponseDAO?

    companion object {
        @Volatile
        private var INSTANCE: BlogDatabase? = null
        fun getInstance(context: Context?): BlogDatabase? {
            synchronized(this) {

                // Copy the current value of INSTANCE to a local variable so Kotlin can smart cast.
                // Smart cast is only available to local variables.
                var instance = INSTANCE
                // If instance is `null` make a new database instance.
                if (instance == null) {
                    instance = context?.applicationContext?.let {
                        Room.databaseBuilder(
                            it,
                            BlogDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                    // Assign INSTANCE to the newly created database.
                    INSTANCE = instance
                }
                return instance
            }

        }
    }
}