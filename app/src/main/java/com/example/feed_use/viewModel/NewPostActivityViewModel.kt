package com.example.feed_use.viewModel


import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Post
import com.example.feed_use.dataprovider.DataProviderUser
import com.example.feed_use.repository.RepositoryPostImp
import com.example.feed_use.repository.RepositoryUserImp

class NewPostActivityViewModel:ViewModel() {

//    private var _posts = MutableLiveData<MutableList<Post>>()
//    var posts: LiveData<MutableList<Post>> = _posts
    private val repositoryPost = RepositoryPostImp()
    private val repositoryUserImp = RepositoryUserImp()

    fun insertPost(post: Post){
        repositoryPost.insertPost(post)
        repositoryUserImp.insertNuberPost(DataProviderUser.user)
    }
}