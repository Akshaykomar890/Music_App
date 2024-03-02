package com.example.musicapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.musicapp.databinding.ActivityEachCategoryBinding

class EachCategory : AppCompatActivity() {
    lateinit var binding: ActivityEachCategoryBinding
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
    }



}