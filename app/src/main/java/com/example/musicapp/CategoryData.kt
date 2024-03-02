package com.example.musicapp

data class CategoryData(val name:String,val coverUrl:String, val songs:List<String>){
    constructor():this("","", listOf())

}

