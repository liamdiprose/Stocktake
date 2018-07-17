package com.liamdiprose.myitems

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import android.database.

typealias Category = String
typealias Item = String
typealias SearchResult = String


class ItemStore(val db: Any) {

    var categories = listOf<Category>("Cords", "Networking", "Promotional")

    fun getCategories(parent: Category?): Observable<Category> {
        return this.categories
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
        TODO()
    }

    /** TODO: Prefetching, Getting Updates */
}