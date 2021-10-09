package com.example.feed_use.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import com.example.feed_use.R
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityNewPostBinding
import com.example.feed_use.dataprovider.DataProviderUser
import com.example.feed_use.viewModel.NewPostActivityViewModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*


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
        setupView()
    }


    private fun setToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar_newpost)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24)
    }

    private fun setupView() {
        binding.apply {
            Picasso.get().load(DataProviderUser.user.imageProfile).into(imageViewProfile)
            textViewNameUser.text = DataProviderUser.user.nameProfile
        }
    }

    private fun setupViewModel() {
        newPostActivityViewModel = ViewModelProvider(this).get(NewPostActivityViewModel::class.java)
    }


    private fun setButton() {
        binding.apply {
            editTextPost.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    val post: String = p0.toString().trim()
                    buttonPost.isEnabled = post.isNotEmpty()
                }

                override fun afterTextChanged(p0: Editable?) {

                }
            }
            )

            buttonPost.setOnClickListener {
                val post = Post(
                    generateIdPost() + DataProviderUser.user.nameProfile,
                    DataProviderUser.user.imageProfile,
                    binding.editTextPost.text.toString(),
                    getTimeNow(),
                    DataProviderUser.user.nameProfile,
                    0,
                    0,
                    null
                )
                newPostActivityViewModel.insertPost(post)
                finish()
            }
        }
    }

    private fun generateIdPost(): String {
        return Calendar.getInstance().time.toString()
            .replace(" ", "")
            .replace(":", "")
            .replace("+", "")
    }

    private fun getTimeNow():String{
        val date = Calendar.getInstance().time

        val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        return dateTimeFormat.format(date)
//        tvDateTime.text = dateTimeFormat.format(date)
//
//        dateTimeFormat = SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault())
//        tvDate.text = dateTimeFormat.format(date)
//
//        dateTimeFormat = SimpleDateFormat("K:mm a", Locale.getDefault())
//        tvTime.text = dateTimeFormat.format(date)
//
//        tvFullDateTime.text = date.toString()
    }
}