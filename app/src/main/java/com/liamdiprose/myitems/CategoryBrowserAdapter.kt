package com.liamdiprose.myitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.row.view.*


class CategoryBrowserAdapter(val categoryStore: ItemStore) : RecyclerView.Adapter<CategoryViewHolder>() {
    private var currentCategory: Category? = null
    private var newCategorySubscription: Disposable? = null

    // TODO: In-Memory Tree for categories/items  (cached)
    private var tree: MutableList<Category> = mutableListOf()

    fun setCurrentCategory(category: Category?) {
        this.currentCategory = category
        setupNewCategoriesSubscription()
    }

    private fun setupNewCategoriesSubscription() {
        this.newCategorySubscription?.dispose()
        this.newCategorySubscription = this.categoryStore.getCategories(this.currentCategory)
                .subscribeBy {
                    this.tree.add(it)
                    notifyItemChanged(this.tree.size - 1)
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        // Create a View from inflating
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row, parent, false)

        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Return how many things cached that are for this.currentCategory
        return this.tree.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // Add data to view elements (textVeiw, etc)
        val itemTitle = this.tree.get(position)
        holder.titleText = itemTitle.name
    }
}

class CategoryViewHolder(val v: View) : RecyclerView.ViewHolder(v) {
    var titleText: String
        get() = this.v.textView.text.toString()
        set(title) = this.v.textView.setText(title)
}