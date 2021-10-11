package com.example.feed_use.repository

import com.example.feed_use.data.User
import kotlinx.coroutines.flow.Flow


interface RepositoryUser {


    suspend fun getUser(): Flow<User>

    fun insertNuberPost(user: User)
}