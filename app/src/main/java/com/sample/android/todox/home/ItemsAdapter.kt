package com.sample.android.todox.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.android.todox.R
import com.sample.android.todox.stores.items.Item

private class ItemViewHolder(itemView: View, val clickListener: ItemsAdapter.OnItemClicked) : RecyclerView.ViewHolder(itemView) {

    val titleTV: TextView by lazy {
        itemView.findViewById<TextView>(R.id.itemTitleTV)
    }

    val descriptionTV: TextView by lazy {
        itemView.findViewById<TextView>(R.id.itemDescriptionTV)
    }

    fun bind(item: Item) {
        titleTV.text = item.title
        descriptionTV.text = item.description

        itemView.setOnClickListener { clickListener.onItemClicked(adapterPosition, item) }
    }
}

class ItemsAdapter(val items: MutableList<Item>, val clickListener: OnItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClicked {
        fun onItemClicked(position: Int, item: Item)
    }

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
        return ItemViewHolder(view, clickListener)
    }

    fun addItems(items: List<Item>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(position: Int, item: Item) {
        items.add(item)
        notifyItemInserted(position)
    }

}