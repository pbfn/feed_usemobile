package com.example.feed_use.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feed_use.data.Post
import com.example.feed_use.R
import com.example.feed_use.activity.NewPostActivity
import com.example.feed_use.adapters.AdapterPost
import com.example.feed_use.databinding.FragmentFeedBinding
import com.example.feed_use.viewModel.FeedFragmentViewModel


class FeedFragment : Fragment() {

    private lateinit var adapterPost: AdapterPost
    private lateinit var binding: FragmentFeedBinding
    private lateinit var feedFragmentViewModel: FeedFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setView()
        observeData()
    }

    private fun setRecyclerView(posts: MutableList<Post>) {
        val layout = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterPost = AdapterPost(posts, requireContext())
        binding.recyclerViewPosts.apply {
            layoutManager = layout
            adapter = adapterPost
            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(context, R.drawable.divider_post)!!)
            addItemDecoration(itemDecoration)
        }
    }

    private fun setupViewModel() {
        feedFragmentViewModel = ViewModelProvider(this).get(FeedFragmentViewModel::class.java)
        feedFragmentViewModel.getPosts()
    }

    private fun observeData() {
        feedFragmentViewModel.posts.observe(viewLifecycleOwner, { posts ->
            setRecyclerView(posts)
        })
    }

    private fun setView(){
        binding.editTextNewPost.setOnClickListener {
            val intent = Intent(context, NewPostActivity::class.java)
            startActivity(intent)
        }
    }

}