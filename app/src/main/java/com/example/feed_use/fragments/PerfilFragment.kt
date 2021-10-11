package com.example.feed_use.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.feed_use.R
import com.example.feed_use.data.User
import com.example.feed_use.databinding.FragmentPerfilBinding
import com.example.feed_use.dataprovider.DataProviderUser
import com.example.feed_use.viewModel.PerfilFragmentViewModel
import com.squareup.picasso.Picasso


class PerfilFragment : Fragment() {

    private lateinit var binding: FragmentPerfilBinding
    private lateinit var perfilFragmentViewModel: PerfilFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPerfilBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeData()
    }

    private fun setupViewModel() {
        perfilFragmentViewModel = ViewModelProvider(this).get(PerfilFragmentViewModel::class.java)
        perfilFragmentViewModel.getUser()
    }

    private fun observeData() {
        perfilFragmentViewModel.user.observe(viewLifecycleOwner, { user ->
            setupView(user)
        })
    }

    private fun setupView(user: User) {
        binding.apply {
            Picasso.get().load(user.imageProfile).into(imageViewPerfil)
            textViewNamePerfil.text = user.nameProfile
            textViewQtdPosts.text = user.qtdPosts.toString()
        }
    }

}