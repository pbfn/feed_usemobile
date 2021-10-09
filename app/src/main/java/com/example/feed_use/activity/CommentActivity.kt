package com.example.feed_use.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feed_use.R
import com.example.feed_use.adapters.AdapterComment
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityCommentBinding
import com.example.feed_use.viewModel.CommentActivityViewModel
import com.squareup.picasso.Picasso

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private lateinit var adapterComment: AdapterComment
    private lateinit var post: Post
    private lateinit var commentActivityViewModel: CommentActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setupViewModel()
        getBundle()
        setupView()
        setRecyclerView()
        obseveData()
    }


    private fun getBundle() {
        post = intent.getSerializableExtra("post") as Post
    }

    private fun setupViewModel() {
        commentActivityViewModel = ViewModelProvider(this).get(CommentActivityViewModel::class.java)
    }

    private fun setToolbar() {
        val actionBar = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar?.setCustomView(R.layout.action_bar_newcomment)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_ios_24)
    }

    private fun setupView() {
        binding.apply {
            include.apply {
                textViewPost.text = post.post
                Picasso.get().load(post.imageProfile).into(imageViewProfile)
                textViewDatePost.text = post.datePost
                textViewQtdComments.text = post.numberComments.toString()
                textViewQtdLikes.text = post.numberLikes.toString()
                textViewNameProfile.text = post.nameProfilePost
            }

            includeComment.imageViewSendCooment.setOnClickListener {
                saveComment(binding.includeComment.editTextNewComment.text.toString())
            }
            Picasso.get().load(post.imageProfile).into(includeComment.imageViewProfile)

        }
    }

    private fun obseveData() {
        commentActivityViewModel.posts.observe(this, {
            adapterComment.notifyDataSetChanged()
        })
    }

    private fun setRecyclerView() {
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterComment = post.comments?.let { AdapterComment(it) }!!
        binding.recyclerViewComments.apply {
            layoutManager = layout
            adapter = adapterComment
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            AppCompatResources.getDrawable(context, R.drawable.divider_comment)?.let {
                itemDecoration.setDrawable(
                    it
                )
            }
            addItemDecoration(itemDecoration)
        }
    }

    private fun saveComment(comment: String) {
        //TODO VERIFICAR SE A STRING ESTA VAZIA, SE ESTIVER VAZIA DEIXAR O NEGOCIO DE COMENTARIO VERMELHO E DAR UM AVISO COM O TOAST
        if (comment.isEmpty()) {
            Toast.makeText(this, "Digite um comentário por favor", Toast.LENGTH_SHORT).show()
        } else {
            commentActivityViewModel.insetComment(post, comment)
            binding.includeComment.editTextNewComment.text.clear()
            closeKeyboard()
        }
    }

    private fun closeKeyboard() {
        val view: View? = currentFocus
        view?.let {
            val imm: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
}