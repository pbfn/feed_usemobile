package com.example.feed_use.data

import java.io.Serializable

data class Post(
    val idPost:String,
    val imageProfile:String,
    val post:String,
    val datePost:String,
    val nameProfilePost:String,
    val numberLikes:Int,
    var numberComments:Int,
    val comments: MutableList<Comment>?
):Serializable


