package com.example.feed_use.repository

import com.example.feed_use.data.Post
import kotlinx.coroutines.flow.Flow

interface RepositoryPost {

    suspend fun getAllPosts(): Flow<MutableList<Post>>

    fun insertPost(post: Post)

    fun editPost(post: Post)
}