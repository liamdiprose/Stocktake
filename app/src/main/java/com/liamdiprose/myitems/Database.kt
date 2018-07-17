package com.liamdiprose.myitems

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


@Database(entities = arrayOf(Category::class), version = 1)
abstract class CategoryDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {
        private var INSTANCE: CategoryDataBase? = null

        fun getInstance(context: Context): CategoryDataBase? {
            if (INSTANCE == null) {
                synchronized(CategoryDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CategoryDataBase::class.java, "categories.db")
                            .allowMainThreadQueries()
                            .build()
                }
            }
            return INSTANCE
        }
        fun destoryInterface() {
            INSTANCE = null
        }
    }
}
