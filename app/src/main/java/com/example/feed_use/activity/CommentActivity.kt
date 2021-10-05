package com.example.feed_use.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundle()
    }



    private fun getBundle(){
        post = intent.getSerializableExtra("post") as Post
        binding.include.textViewPost.text = post.post
    }

}