package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.Adapter.SongListAdapter
import com.example.musicapp.databinding.ActivityEachCategoryBinding
import com.google.firebase.firestore.FirebaseFirestore

class EachCategory : AppCompatActivity() {
    lateinit var binding: ActivityEachCategoryBinding
    lateinit var adapter:SongListAdapter
    companion object{
        lateinit var category:CategoryData
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEachCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nameTextView1.text = category.name

        Glide.with(binding.coverImageView).load(category.coverUrl)
            .apply(
                RequestOptions().transform(RoundedCorners(32))
            )
            .into(binding.coverImageView)
            getCategories()

    }
    fun getCategories(){
        FirebaseFirestore.getInstance().collection("category").get()
            .addOnSuccessListener {
                val list = it.toObjects(CategoryData::class.java)
                getAdapter(list)
            }
    }
    fun getAdapter(list:List<CategoryData>){
        adapter = SongListAdapter(category.songs)
        binding.songsListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.songsListRecyclerView.adapter = adapter
        adapter.setGetClick(object :SongListAdapter.onItemClickListers{
            override fun getClick(position: Int) {
                EachCategory.category = list[position]
            }

        })
    }





}