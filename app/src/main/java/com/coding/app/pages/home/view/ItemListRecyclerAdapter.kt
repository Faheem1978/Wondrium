package com.coding.app.pages.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.coding.app.R
import com.coding.app.databinding.ItemLayoutProductBinding


class ItemListRecyclerAdapter(var listItemClickCallback: ListItemClickCallback) :
    RecyclerView.Adapter<ItemListRecyclerAdapter.ItemViewHolder>() {
    var dummyData = ArrayList<String>()
    lateinit var binding: ItemLayoutProductBinding
    fun updateList(list: List<String>) {
        dummyData.clear()
        dummyData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        binding =
            ItemLayoutProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = dummyData[position]
        holder.name.text = data
        holder.name.setOnClickListener { openLeadDetailsPage(data) }

    }


    private fun openLeadDetailsPage(name: String) {

        listItemClickCallback.onClickItem(name)
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: Button = itemView.findViewById(R.id.pName)

    }
}