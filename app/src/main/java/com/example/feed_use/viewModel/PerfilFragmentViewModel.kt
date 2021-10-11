package com.example.feed_use.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.feed_use.data.User
import com.example.feed_use.repository.RepositoryUserImp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PerfilFragmentViewModel : ViewModel() {


    private var _user = MutableLiveData<User>()
    var user: LiveData<User> = _user
    private val repository = RepositoryUserImp()

    fun getUser() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getUser().collect {
                CoroutineScope(Dispatchers.Main).launch {
                    _user.value = it
                }
            }
        }

    }


}