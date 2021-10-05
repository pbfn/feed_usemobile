package com.example.feed_use.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feed_use.R
import com.example.feed_use.adapters.AdapterComment
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityCommentBinding

class CommentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCommentBinding
    private lateinit var adapterComment: AdapterComment
    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundle()
        setupView()
        setRecyclerView()
    }



    private fun getBundle(){
        post = intent.getSerializableExtra("post") as Post
    }

    private fun setupView(){
        binding.include.textViewPost.text = post.post
        binding.includeComment.imageViewSendCooment.setOnClickListener {
            saveComment(binding.includeComment.editTextNewComment.text.toString())
        }
    }


    private fun setRecyclerView(){
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapterComment = AdapterComment(post.comments)
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

    private fun saveComment(comment:String){
        //TODO ENVIAR PARA O FIREBASE E CHAMAR A ATUALIZAR A RECYCLER
        //TODO VERIFICAR SE A STRING ESTA VAZIA, SE ESTIVER VAZIA DEIXAR O NEGOCIO DE COMENTARIO VERMELHO E DAR UM AVISO COM O TOAST
        Toast.makeText(baseContext,binding.includeComment.editTextNewComment.text.toString(),Toast.LENGTH_LONG).show()
    }
}