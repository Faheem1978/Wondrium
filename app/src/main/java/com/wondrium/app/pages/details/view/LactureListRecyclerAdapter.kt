package com.wondrium.app.pages.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wondrium.app.databinding.ItemLayoutLecturesBinding
import com.wondrium.app.pages.details.models.Lectures


class LactureListRecyclerAdapter(var listItemClickCallback: ListItemClickCallback) :
    RecyclerView.Adapter<LactureListRecyclerAdapter.ItemViewHolder>() {
    var dummyData = ArrayList<Lectures>()
    lateinit var binding: ItemLayoutLecturesBinding
    public fun updateList(list: ArrayList<Lectures>) {
        dummyData.clear()
        dummyData.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {


        binding =
            ItemLayoutLecturesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return dummyData.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = dummyData[position]
        binding.pLactureName.text = data.lectureName
        binding.pLactureDescription.text = data.lectureDescription

        holder.itemView.setOnClickListener { openLeadDetailsPage(data) }

    }


    private fun openLeadDetailsPage(Lectures: Lectures) {
        val bundle = Bundle()
        listItemClickCallback.onClickItem(bundle)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}