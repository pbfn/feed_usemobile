package com.example.feed_use.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.feed_use.Post
import com.example.feed_use.databinding.ItemAdapterPostBinding
import com.squareup.picasso.Picasso


class AdapterPost(private val posts: MutableList<Post>) :
    RecyclerView.Adapter<AdapterPost.ViewHolder>() {

    class ViewHolder(view: ItemAdapterPostBinding) : RecyclerView.ViewHolder(view.root) {
        val imageViewProfile = view.imageViewProfile
        val textViewNameProfile = view.textViewNameProfile
        val texViewDatePost = view.textViewDatePost
        val textViewPost = view.textViewPost
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
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}