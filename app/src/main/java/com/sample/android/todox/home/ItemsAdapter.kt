package com.sample.android.todox.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.android.todox.R
import com.sample.android.todox.stores.items.Item

private class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleTV: TextView by lazy {
        itemView.findViewById<TextView>(R.id.itemTitleTV)
    }

    val descriptionTV: TextView by lazy {
        itemView.findViewById<TextView>(R.id.itemDescriptionTV)
    }

    fun bind(item: Item) {
        titleTV.text = item.title
        descriptionTV.text = item.description
    }

}

class ItemsAdapter(val items: MutableList<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = items[position]
        when (holder) {
            is ItemViewHolder -> holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.items_list_item, parent, false)
        return ItemViewHolder(view)
    }

    fun addItems(items: MutableList<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}