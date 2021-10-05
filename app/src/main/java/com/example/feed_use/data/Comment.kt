package com.example.feed_use.data

import java.io.Serializable

data class Comment(
    val idUser: String,
    val imageUser:String,
    val nameUser:String,
    val comment: String,
    val dateCommet: String
): Serializable
