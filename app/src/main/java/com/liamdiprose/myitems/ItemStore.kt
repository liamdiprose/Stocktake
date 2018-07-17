package com.liamdiprose.myitems

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import com.liamdiprose.myitems.Category

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Room

//typealias Category = String
typealias Item = String
typealias SearchResult = String


class ItemStore(val db: CategoryDao) {

//    var categories = listOf<Category>("Cords", "Networking", "Promotional")

    fun getCategories(parent: Category?): Observable<Category> {
        return db.getCategories(parent?.id)
                .toObservable()
    }

    fun getTopLevelCategories(): Observable<Category> {
        return this.getCategories(null)
    }

    fun searchAll(searchTerm: String): Observable<SearchResult> {
        TODO()
    }

    fun addItem(category: Category, item: Item) {
        TODO()
    }

    fun addCategory(parent: Category?, category: Category) {
        db.insertCategory(Category(null, "Bob", parent?.id))
    }

    /** TODO: Prefetching, Getting Updates */
}