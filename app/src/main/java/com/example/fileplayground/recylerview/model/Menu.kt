package com.example.fileplayground.recylerview.model

data class Menu(
    val nama:String,
    val jumlah:Int,
    val jenis:Category
){
    enum class Category{
        MAKANAN, MINUMAN
    }


}


