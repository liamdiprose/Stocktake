package com.liamdiprose.myitems

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
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
                            .build()
                }
            }
            return INSTANCE
        }
    }
}
