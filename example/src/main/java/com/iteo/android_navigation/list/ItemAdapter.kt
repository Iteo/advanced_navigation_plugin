package com.iteo.android_navigation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.iteo.android_navigation.R
import kotlinx.android.synthetic.main.item.view.text

class ItemAdapter(private val clickListener: (Item) -> Unit) : ListAdapter<Item, ItemViewHolder>(
    object: ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }
}

class ItemViewHolder(view: View) : ViewHolder(view) {

    fun bind(item: Item, clickListener: (Item) -> Unit) = with(itemView) {
        text.text = item.name
        setBackgroundColor(item.color)
        setOnClickListener { clickListener(item) }
    }
}
