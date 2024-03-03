package com.example.musicapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.models.CategoryData
import com.example.musicapp.mainActivity.EachCategory
import com.example.musicapp.R

class NewAdapter(val categoryList:List<CategoryData>)
    :RecyclerView.Adapter<NewAdapter.MyViewHolder>() {
        private lateinit var myListner:onItemClickLister

        interface onItemClickLister{
            fun onItemClick(position: Int)
        }
    fun setItemClickListner(listner:onItemClickLister){
        myListner = listner
    }


    class MyViewHolder(itemView:View,listner:onItemClickLister):RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.eachItemText)
        var imageView = itemView.findViewById<ImageView>(R.id.cover_image)

        init {
            itemView.setOnClickListener{
                listner.onItemClick(adapterPosition)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val connect = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item_recycler_row,parent,false)
        return MyViewHolder(connect,myListner)

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val get = categoryList[position]
        holder.textView.text = get.name
        EachCategory.category = get

        Glide.with(holder.imageView).load(get.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(holder.imageView)

    }


}