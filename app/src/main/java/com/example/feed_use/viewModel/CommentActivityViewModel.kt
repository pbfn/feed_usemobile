package com.example.feed_use.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.example.feed_use.dataprovider.DataProviderUser
import com.example.feed_use.repository.RepositoryPostImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class CommentActivityViewModel:ViewModel() {

    private var _posts = MutableLiveData<MutableList<Post>>()
    var posts: LiveData<MutableList<Post>> = _posts
    private val repository = RepositoryPostImp()


    fun insetComment(post: Post,comment:String){
        val commentFirebase = Comment(
            DataProviderUser.user.idUser,
            DataProviderUser.user.imageProfile,
            DataProviderUser.user.nameProfile,
            comment,
            getTimeNow()
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

    private fun getTimeNow():String{
        val date = Calendar.getInstance().time

        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateTimeFormat.format(date)
    }


}