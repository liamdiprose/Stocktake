
package com.liamdiprose.myitems

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.rxkotlin.subscribeBy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ExpandableListView

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view.setOnClickListener({
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            })
        }
    })
}


class MainActivity : AppCompatActivity() {

    val listAdapter = MyListAdapter()

    fun setName(name: String?) {
        val title = when(name) {
            is String -> R.string.first_names
            else -> R.string.last_names
        }

        setTitle(title)
        this.listAdapter.showLastNames(name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = NameStore()

        model.fetchNames(4)
                .subscribeBy { listAdapter.addName(it) }

        recyclerView_main.layoutManager = LinearLayoutManager(this.applicationContext)

        recyclerView_main.adapter = listAdapter

        recyclerView_main.addOnItemClickListener(object: OnItemClickListener {

            override fun onItemClicked(position: Int, view: View) {
                if (listAdapter.name != null) {
                    setTitle(R.string.app_name)
                    listAdapter.showLastNames(null)
                } else {
                    val name = listAdapter.strings.get(position)
                    setTitle(name)
                    listAdapter.showLastNames(name)
                }
            }

        })
    }

    override fun onBackPressed() {
        if (listAdapter.name == null) {
            super.onBackPressed()
        } else {
            setTitle(R.string.app_name)
            listAdapter.showLastNames(null)
        }
    }
}
