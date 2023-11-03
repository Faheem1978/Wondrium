package com.wondrium.app.pages.home.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wondrium.app.databinding.ItemLayoutProductBinding
import com.wondrium.app.pages.home.models.Products


class ProductListRecyclerAdapter(var listItemClickCallback: ListItemClickCallback) :
    RecyclerView.Adapter<ProductListRecyclerAdapter.ItemViewHolder>() {
    var dummyData = ArrayList<Products>()
    lateinit var binding: ItemLayoutProductBinding
    public fun updateList(list: ArrayList<Products>) {
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
        binding.pName.text = data.productName
        binding.pCourseName.text = data.courseName
        binding.pShortDescription.text = data.productShortDescription
        binding.pShortDescription.movementMethod=ScrollingMovementMethod()

        binding.pImage.load(
            "https://secureimages.teach12.com/tgc/images/m2/wondrium/courses/${data.courseId}/\n" +
                    "portrait/${data.courseId}.jpg "
        )

        holder.itemView.setOnClickListener { openLeadDetailsPage(data) }

    }


    private fun openLeadDetailsPage(products: Products) {
        val bundle = Bundle()
        bundle.putString("id", products.productId.toString())
        listItemClickCallback.onClickItem(bundle)
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }
}