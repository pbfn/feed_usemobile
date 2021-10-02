package com.example.feed_use.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.feed_use.Post
import com.example.feed_use.R
import com.example.feed_use.dataprovider.DataProviderPost
import com.example.feed_use.adapters.AdapterPost
import com.example.feed_use.databinding.FragmentFeedBinding


class FeedFragment : Fragment() {

    private lateinit var adapterPost: AdapterPost
    private lateinit var binding: FragmentFeedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       setRecyclerView()
    }

    private fun setRecyclerView(){
        val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        adapterPost = AdapterPost(DataProviderPost.postList,::clickItem )
        binding.recyclerViewPosts.apply {
            layoutManager = layout
            adapter = adapterPost
            val itemDecoration = DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(context, R.drawable.divider_post)!!)
            addItemDecoration(itemDecoration)
        }
    }

    private fun clickItem(post: Post){
        var direction = FeedFragmentDirections.actionFeedFragmentToPostFragment()
        findNavController().navigate(direction)
    }
}