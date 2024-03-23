package com.example.musicapp.mainActivity

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapp.Adapter.NewAdapter
import com.example.musicapp.Adapter.TopChartsAdapter
import com.example.musicapp.Adapter.TrendingAdapter
import com.example.musicapp.R
import com.example.musicapp.databinding.ActivityMainBinding
import com.example.musicapp.models.CategoryData
import com.example.musicapp.models.EachCategoryData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var Myadapter:NewAdapter
   lateinit var  Trendingadapter:TrendingAdapter
   lateinit var Topchartsadapter:TopChartsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCategories()
        getSection1()
        getSection2()
       
    }


    fun getCategories(){
      FirebaseFirestore.getInstance().collection("category").get()
          .addOnSuccessListener {
              val list = it.toObjects(CategoryData::class.java)
              addToAdapter(list)
          }
    }

    fun addToAdapter(list:List<CategoryData>){
        Myadapter = NewAdapter(list)
        binding.categorieRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.categorieRecyclerView.adapter = Myadapter
        Myadapter.setItemClickListner(object :NewAdapter.onItemClickLister {
            override fun onItemClick(position: Int){
                EachCategory.category = list[position]
                val intent = Intent(this@MainActivity, EachCategory::class.java)
                startActivity(intent)
            }
        })

    }

    fun getSection1(){
        FirebaseFirestore.getInstance().collection("section 1").document("section_1").get()
            .addOnSuccessListener {
                val getData = it.toObject(CategoryData::class.java)
                getData?.apply {
                    binding.trendingRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    binding.trendingRecyclerView.adapter = TrendingAdapter(songs)
                }
            }
    }

    fun getSection2(){
        FirebaseFirestore.getInstance().collection("section2")
            .document("section2").get().addOnSuccessListener {
                val getData = it.toObject(CategoryData::class.java)
                getData?.apply {
                    Topchartsadapter = TopChartsAdapter(songs)
                    binding.topChartsRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
                    binding.topChartsRecyclerView.adapter = Topchartsadapter
                }
            }
    }




}