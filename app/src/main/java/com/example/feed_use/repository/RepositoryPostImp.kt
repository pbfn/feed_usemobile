package com.example.feed_use.repository

import android.util.Log
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RepositoryPostImp : RepositoryPost {

    private val db = Firebase.firestore
    private val pathPost = "posts"

    override suspend fun getAllPosts(): Flow<MutableList<Post>> {
        val postList = ArrayList<Post>()
        //TODO VERIFICAR CHANNEL FLOW
        return channelFlow {
            db.collection(pathPost).get()
                .addOnSuccessListener { postsFromFirebase ->
                    for (postFire in postsFromFirebase) {

                        val commentList = ArrayList<Comment>()
                        if (postFire.data["comments"] != null) {
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
                            postFire.data["idPost"].toString(),
                            postFire.data["imageProfile"].toString(),
                            postFire.data["post"].toString(),
                            postFire.data["datePost"].toString(),
                            postFire.data["nameProfilePost"].toString(),
                            postFire.data["numberLikes"].toString().toInt(),
                            postFire.data["numberComments"].toString().toInt(),
                            commentList,
                        )
                        postList.add(post)
                    }
                    //TODO VERIFICAR LAUNCH
                    launch {
                        send(postList)
                    }
                    Log.d("RepositoryIMP", "Posts foram recuperados")
                }.addOnFailureListener {
                    Log.d("RepositoryIMP", "Posts não foram recuperados")
                }
            awaitClose()
            //TODO VERIFICAR AWAITCLOSE
        }

    }

    override fun insertPost(post: Post) {
        db.collection(pathPost).document(post.idPost).set(post).addOnSuccessListener {
            Log.d("RepositoryIMP", "Post adicionado com sucesso")
        }.addOnFailureListener {
            Log.d("RepositoryIMP", "Post não foi adicionado")
        }
    }


    override fun editPost(post: Post) {
        db.collection("posts").document(post.idPost).update(
            "numberComments", post.numberComments+1,
            "comments", post.comments,
        ).addOnSuccessListener {
            Log.d("RepositoryIMP", "Post editado com sucesso")
        }.addOnFailureListener {
            it.toString()
            Log.d("RepositoryIMP", "Post não foi editao")
        }
    }

    override fun editPostLike(post: Post) {
        db.collection("posts").document(post.idPost).update(
            "numberLikes", post.numberLikes+1,
        ).addOnSuccessListener {
            Log.d("RepositoryIMP", "Post editado com sucesso")
        }.addOnFailureListener {
            it.toString()
            Log.d("RepositoryIMP", "Post não foi editao")
        }
    }

}