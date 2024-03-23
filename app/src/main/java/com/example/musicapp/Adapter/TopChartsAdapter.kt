package com.example.musicapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.R
import com.example.musicapp.mainActivity.PlayerActivity
import com.example.musicapp.models.CategoryData
import com.example.musicapp.models.EachCategoryData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import java.util.zip.Inflater

class TopChartsAdapter(var songs:List<String>)
    :RecyclerView.Adapter<TopChartsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
            val textView:TextView = itemView.findViewById(R.id.topChartText)
            val imageView:ImageView = itemView.findViewById(R.id.topChartImage)

        fun getEachSongs (getId:String){
            FirebaseFirestore.getInstance().collection("songs")
                .document(getId).get().addOnSuccessListener {
                    val get = it.toObject(EachCategoryData::class.java)
                    if(get != null){
                        textView.text = get.name
                        Glide.with(imageView).load(get.imageUrl).apply(
                            RequestOptions().transform(RoundedCorners(32))
                        ).into(imageView)
                        itemView.setOnClickListener {
                            it.context.startActivities(arrayOf(Intent(itemView.context,PlayerActivity::class.java)))
                            PlayerActivity.category = get
                        }
                }

                }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val connect = LayoutInflater.from(parent.context).inflate(R.layout.top_charts,parent,false)
        return MyViewHolder(connect)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val getData = songs[position]
        holder.getEachSongs(getData)
    }
}