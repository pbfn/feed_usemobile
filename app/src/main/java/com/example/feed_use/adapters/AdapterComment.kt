package com.example.feed_use.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feed_use.Comment
import com.example.feed_use.databinding.ItemAdapterCommentBinding
import com.squareup.picasso.Picasso

class AdapterComment(private val comments: MutableList<Comment>) :
    RecyclerView.Adapter<AdapterComment.ViewHolder>() {

    class ViewHolder(view: ItemAdapterCommentBinding) : RecyclerView.ViewHolder(view.root) {
        val textViewComment = view.textViewComment
        val textViewDateComment = view.textViewDateComment
        val textViewNameComment = view.textViewNameComment
        val imageViewComment = view.imageViewProfile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemAdapterCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(comments[position].imageUser).into(holder.imageViewComment)
        holder.apply {
            textViewComment.text = comments[position].comment
            textViewDateComment.text = comments[position].dateCommet
            textViewNameComment.text = comments[position].nameUser
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }


}