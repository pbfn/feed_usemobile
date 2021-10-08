package com.example.feed_use.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.example.feed_use.dataprovider.UserDataProvider
import com.example.feed_use.repository.RepositoryPostImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CommentActivityViewModel:ViewModel() {

    private var _posts = MutableLiveData<MutableList<Post>>()
    var posts: LiveData<MutableList<Post>> = _posts
    private val repository = RepositoryPostImp()


    fun insetComment(post: Post,comment:String){
        val commentFirebase = Comment(
            UserDataProvider.user.idUser,
            UserDataProvider.user.imageProfile,
            UserDataProvider.user.nameProfile,
            comment,
            ""
        )
        post.comments?.add(commentFirebase)
        repository.editPost(post)
        getPosts()
    }

    private fun getPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getAllPosts().collect {
                _posts.postValue(it)
            }
        }
    }


}