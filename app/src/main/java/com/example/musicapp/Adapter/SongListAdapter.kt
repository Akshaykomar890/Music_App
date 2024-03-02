package com.example.musicapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.EachCategory
import com.example.musicapp.EachCategoryData
import com.example.musicapp.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class SongListAdapter(var list: List<String>)
    :RecyclerView.Adapter<SongListAdapter.NewViewHolder>() {
        lateinit var myListner:onItemClickListers

        interface onItemClickListers{
            fun getClick(position: Int)
        }
    fun setGetClick(listner:onItemClickListers){
        myListner = listner
    }


    class NewViewHolder(itemView: View,listner:onItemClickListers):RecyclerView.ViewHolder(itemView) {
        val textView1: TextView = itemView.findViewById(R.id.onClickSongName)
        val textView2: TextView = itemView.findViewById(R.id.onClickSongArtist)
        val imageView:ImageView = itemView.findViewById(R.id.onClickImageView)

        init {
            itemView.setOnClickListener{
                listner.getClick(adapterPosition)
            }
        }
        fun getSongs(id:String){
            FirebaseFirestore.getInstance().collection("songs")
                .document(id).get().addOnSuccessListener {
                    val get = it.toObject(EachCategoryData::class.java)
                    if (get != null) {
                        textView1.text = get.name
                        textView2.text = get.subtitle

                    }
                    if (get != null) {
                        Glide.with(imageView).load(get.imageUrl).apply(
                            RequestOptions().transform(RoundedCorners(32))
                        ).into(imageView)
                    }
                }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val connect = LayoutInflater.from(parent.context).inflate(R.layout.on_click_category,parent,false)
        return NewViewHolder(connect,myListner)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val getPosition = list[position]
        holder.getSongs(getPosition)

    }
}