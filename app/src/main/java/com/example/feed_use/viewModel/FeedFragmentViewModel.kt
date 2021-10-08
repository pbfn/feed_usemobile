package com.example.feed_use.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Post
import com.example.feed_use.repository.RepositoryPostImp

class FeedFragmentViewModel : ViewModel() {

    private var _posts = MutableLiveData<MutableList<Post>>()
    var posts: LiveData<MutableList<Post>> = _posts
    private val repository = RepositoryPostImp()

    fun getPosts() {
        _posts.value = repository.getAllPosts()
    }

}