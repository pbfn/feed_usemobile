package com.example.feed_use.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Post
import com.example.feed_use.repository.RepositoryPostImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeedFragmentViewModel : ViewModel() {

    private var _posts = MutableLiveData<MutableList<Post>>()
    var posts: LiveData<MutableList<Post>> = _posts
    private val repository = RepositoryPostImp()

     fun getPosts() {
        Log.d("FeedFragmentViewModel","Post chegaram")
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllPosts().collect {
                CoroutineScope(Dispatchers.Main).launch {
                    Log.d("FeedFragmentViewModelT","Post chegaram")
                    _posts.value = it
                }
            }
        }
    }


    fun editNumberLike(post: Post) {
        repository.editPostLike(post)
    }

}