package com.example.feed_use.repository

import com.example.feed_use.data.Post

interface RepositoryPost {

    fun getAllPosts():MutableList<Post>

    fun insertPost(post: Post)
}