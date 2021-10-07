package com.example.feed_use.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.feed_use.R
import com.example.feed_use.databinding.ActivityNewPostBinding


class NewPostActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNewPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
    }


    private fun setToolbar(){
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar_newpost)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
    }
}