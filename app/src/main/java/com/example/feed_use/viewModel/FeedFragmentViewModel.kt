package com.example.feed_use.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FeedFragmentViewModel : ViewModel() {

    private var _posts = MutableLiveData<MutableList<Post>>()
    var posts: LiveData<MutableList<Post>> = _posts
    private var db = Firebase.firestore


    fun getPosts() {
        db.collection("post").get().addOnSuccessListener { postsFromFirebase ->
            val postList = ArrayList<Post>()


            for (postFire in postsFromFirebase) {
                val commentsFirebase = postFire.data["comments"] as MutableList<*>
                val commentList = ArrayList<Comment>()
                for (comment in commentsFirebase) {
                    val commentLocal = Comment(
                        (comment as HashMap<*, *>)["idUser"].toString(),
                        (comment as HashMap<*, *>)["imageUser"].toString(),
                        (comment as HashMap<*, *>)["nameUser"].toString(),
                        (comment as HashMap<*, *>)["comment"].toString(),
                        (comment as HashMap<*, *>)["dateCommet"].toString()
                    )
                    commentList.add(commentLocal)
                }

                val post = Post(
                    postFire.data["idPost"].toString(),
                    postFire.data["imageProfile"].toString(),
                    postFire.data["post"].toString(),
                    postFire.data["datePost"].toString(),
                    postFire.data["nameProfilePost"].toString(),
                    commentList,
                )
                postList.add(post)
            }
            _posts.value = postList
        }
    }
}