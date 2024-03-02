package com.example.musicapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.musicapp.Adapter.NewAdapter
import com.example.musicapp.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlin.math.E

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var Myadapter:NewAdapter
    val s =EachCategory()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCategories()
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
                val intent = Intent(this@MainActivity,EachCategory::class.java)
                startActivity(intent)
            }
        })

    }

}