package com.example.feed_use.repository

import android.util.Log
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RepositoryPostImp:RepositoryPost {

    private val db = Firebase.firestore
    private val pathPost = "posts"

    override fun getAllPosts(): MutableList<Post> {

        val postList = ArrayList<Post>()

        db.collection(pathPost).get().addOnSuccessListener { postsFromFirebase ->
            for (postFire in postsFromFirebase) {
                postFire.data
                val commentList = ArrayList<Comment>()
                if(postFire.data["comments"] != null){
                    val commentsFirebase = postFire.data["comments"] as MutableList<*>
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
                }

                val post = Post(
                    postFire.data["imageProfile"].toString(),
                    postFire.data["post"].toString(),
                    postFire.data["datePost"].toString(),
                    postFire.data["nameProfilePost"].toString(),
                    commentList,
                )
                postList.add(post)
            }
        }
        return postList
    }

    override fun insertPost(post: Post) {
        db.collection(pathPost).add(post).addOnSuccessListener {
            Log.d("RepositoryIMP","Post adicionado com sucesso")
        }.addOnFailureListener{
            Log.d("RepositoryIMP","Post não foi adicionado")
        }
    }

}