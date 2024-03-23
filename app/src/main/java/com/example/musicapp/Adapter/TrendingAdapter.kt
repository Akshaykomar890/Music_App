package com.example.musicapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.R
import com.example.musicapp.mainActivity.EachCategory
import com.example.musicapp.mainActivity.MainActivity
import com.example.musicapp.mainActivity.PlayerActivity
import com.example.musicapp.models.CategoryData
import com.example.musicapp.models.EachCategoryData
import com.google.firebase.firestore.FirebaseFirestore

class TrendingAdapter(var song:List<String>)
    :RecyclerView.Adapter<TrendingAdapter.MyViewHolder>() {

    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        var imageView:ImageView = itemView.findViewById(R.id.trendingImage)
        var textView:TextView = itemView.findViewById(R.id.eachTrendingText)

        fun getSongs(id:String){
            FirebaseFirestore.getInstance().collection("songs")
                .document(id).get().addOnSuccessListener {
                    val get = it.toObject(EachCategoryData::class.java)
                    if (get != null) {
                        textView.text = get.name
                    }
                    if (get != null)
                    {
                        Glide.with(imageView).load(get.imageUrl).apply(
                            RequestOptions().transform(RoundedCorners(32))
                        ).into(imageView)
                    }
                    if (get != null) {
                        itemView.setOnClickListener {
                            it.context.startActivities(arrayOf(Intent(itemView.context,PlayerActivity::class.java)))
                            PlayerActivity.category = get

                        }
                    }
                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val connect  = LayoutInflater.from(parent.context).inflate(R.layout.each_trending,parent,false)
        return MyViewHolder(connect)
    }

    override fun getItemCount(): Int {
         return song.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val get = song[position]
        holder.textView.text = get
        holder.getSongs(get)





    }
}