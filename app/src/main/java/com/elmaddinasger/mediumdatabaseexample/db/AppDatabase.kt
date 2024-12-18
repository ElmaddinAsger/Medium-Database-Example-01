package com.elmaddinasger.mediumdatabaseexample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CustomerEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun customerDao():CustomerDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,"AppDatabase"
                ).build()
                INSTANCE =instance
                instance
            }
        }
    }
}