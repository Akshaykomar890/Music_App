package com.example.musicapp.models

data class EachCategoryData(
    val id:String,
    val imageUrl:String,
    val name:String,
    val subtitle:String,
    val url:String
){
    constructor():this("","","","","")
}
