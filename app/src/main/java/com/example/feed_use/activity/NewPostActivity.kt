package com.example.feed_use.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.feed_use.R
import com.example.feed_use.data.Comment
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityNewPostBinding
import com.example.feed_use.dataprovider.UserDataProvider
import com.example.feed_use.viewModel.NewPostActivityViewModel


class NewPostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewPostBinding
    private lateinit var newPostActivityViewModel: NewPostActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setupViewModel()
        setButton()
    }


    private fun setToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar_newpost)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
    }

    private fun setupViewModel() {
        newPostActivityViewModel = ViewModelProvider(this).get(NewPostActivityViewModel::class.java)
    }


    private fun setButton() {

        binding.buttonPost.setOnClickListener {
            val post = Post(
                UserDataProvider.user.imageProfile,
                binding.editTextPost.text.toString(),
                "",
                UserDataProvider.user.imageProfile,
                null,
            )
            newPostActivityViewModel.insertPost(post)
        }
    }
}