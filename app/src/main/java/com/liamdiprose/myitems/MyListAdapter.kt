package com.liamdiprose.myitems

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row.view.*
import java.util.*
import kotlin.collections.HashMap


class MyListAdapter : RecyclerView.Adapter<MyViewHolder>() {


    var strings: MutableList<String> = mutableListOf("Liam", "Bob", "Jeff")

    val liamLastNames = listOf("Diprose")
    val bobLastNames = listOf("Bobrose")
    val jeffLastNames = listOf("Stein")

    val lastNameMap: HashMap<String, List<String>> = hashMapOf<String, List<String>>("Liam" to liamLastNames, "Bob" to bobLastNames, "Jeff" to jeffLastNames)

    var name: String? = null;

    override fun getItemCount(): Int = when (this.name) {
        is String -> this.lastNameMap[this.name as String]!!.size
        else -> this.strings.size
    }

    fun addName(name: String) {
        strings.add(name)
        notifyItemInserted(strings.size - 1)
    }

    public fun showLastNames(name: String?) {
        this.name = name
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.v.textView.text = when (this.name) {
            is String -> this.lastNameMap[this.name as String]!!.get(position)
            else -> this.strings.get(position)
        }
//        holder.v.textView.setText(text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.row, parent, false)


        return MyViewHolder(view)
    }
}

class MyViewHolder(val v: View) : RecyclerView.ViewHolder(v) {


}