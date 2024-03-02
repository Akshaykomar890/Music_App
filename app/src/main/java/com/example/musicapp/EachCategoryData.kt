package com.example.musicapp

data class EachCategoryData(
    val id:String,
    val imageUrl:String,
    val name:String,
    val subtitle:String,
    val url:String
){
    constructor():this("","","","","")
}
