package com.coding.app.pages.details.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.coding.app.R
import com.coding.app.databinding.ItemLayoutLecturesBinding


class ImageListRecyclerAdapter() :
    RecyclerView.Adapter<ImageListRecyclerAdapter.ItemViewHolder>() {
    var data = ArrayList<String>()
    lateinit var binding: ItemLayoutLecturesBinding
    public fun updateList(list: ArrayList<String>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding =
            ItemLayoutLecturesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val imageUrl = data[position]
        holder.image.load(imageUrl) {
            placeholder(R.drawable.ic_loading)
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
    }
}