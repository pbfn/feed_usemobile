package com.example.feed_use.repository


import android.util.Log
import com.example.feed_use.data.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class RepositoryUserImp : RepositoryUser {

    private val db = Firebase.firestore
    private val pathUser = "users"


    override fun editUser(user: User) {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(): Flow<User> {
        var user: User = User("1","Carregando","...",0)
        return channelFlow {
            db.collection(pathUser).get()
                .addOnSuccessListener { userFromFireBase ->
                    for (users in userFromFireBase) {
                        user = User(
                            users.data["idUser"].toString(),
                            users.data["nameProfile"].toString(),
                            users.data["imageProfile"].toString(),
                            users.data["qtdPosts"].toString().toInt(),
                        )
                    }
                    launch {
                        send(user)
                    }
                }.addOnFailureListener { erro ->
                    Log.d("Erro", erro.toString())
                }

            awaitClose()
        }


    }

    fun addUser(user: User) {
        db.collection(pathUser).document(user.idUser).set(user).addOnSuccessListener {
            Log.d("RepositoryIMP", "User adicionado com sucesso")
        }
    }

}