package com.example.feed_use.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feed_use.activity.CommentActivity
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ItemAdapterPostBinding
import com.squareup.picasso.Picasso


class AdapterPost(private val posts: List<Post>, var context: Context) :
    RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    class ViewHolder(view: ItemAdapterPostBinding) : RecyclerView.ViewHolder(view.root) {
        val imageViewProfile = view.imageViewProfile
        val textViewNameProfile = view.textViewNameProfile
        val texViewDatePost = view.textViewDatePost
        val textViewPost = view.textViewPost
        val textViewQtdLikes = view.textViewQtdLikes
        val textViewQtdComments = view.textViewQtdComments
        val imageViewComment = view.imageViewComment
        val imageViewLike = view.imageViewLike
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAdapterPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(posts[position].imageProfile).into(holder.imageViewProfile)
        holder.apply {
            textViewNameProfile.text = posts[position].nameProfilePost
            texViewDatePost.text = posts[position].datePost
            textViewPost.text = posts[position].post
            imageViewComment.setOnClickListener {
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("post", posts[position])
                context.startActivity(intent)
            }
           itemView.setOnClickListener {
                val intent = Intent(context, CommentActivity::class.java)
                intent.putExtra("post", posts[position])
                context.startActivity(intent)
            }

            imageViewLike.setOnClickListener{

            }
            textViewQtdComments.text = posts[position].numberComments.toString()
            textViewQtdLikes.text = posts[position].numberLikes.toString()
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}