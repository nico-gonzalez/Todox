package com.sample.android.todox.home

import android.support.v7.util.DiffUtil
import android.support.v7.util.DiffUtil.Callback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.android.todox.R
import com.sample.android.todox.common.arch.stores.items.Item

private class ItemViewHolder(itemView: View,
    val clickListener: ItemsAdapter.OnItemClicked) : RecyclerView.ViewHolder(itemView) {

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
    val diffResult = DiffUtil.calculateDiff(DiffCallback(this.items, items))
    this.items.clear()
    this.items.addAll(items)
    diffResult.dispatchUpdatesTo(this)
  }
}

class DiffCallback(val oldItems: MutableList<Item>, val newItems: List<Item>) : Callback() {
  override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
      oldItems[oldItemPosition].id == newItems[newItemPosition].id

  override fun getOldListSize(): Int = oldItems.size

  override fun getNewListSize(): Int = newItems.size

  override fun getChangePayload(oldItemPosition: Int,
      newItemPosition: Int): Any? = super.getChangePayload(oldItemPosition, newItemPosition)

  override fun areContentsTheSame(oldItemPosition: Int,
      newItemPosition: Int): Boolean = oldItems[oldItemPosition] == newItems[newItemPosition]
}
