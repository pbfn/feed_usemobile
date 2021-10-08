package com.example.feed_use.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.feed_use.R
import com.example.feed_use.data.Post
import com.example.feed_use.databinding.ActivityMainBinding
import com.example.feed_use.dataprovider.DataProviderComment
import com.example.feed_use.fragments.FeedFragment
import com.example.feed_use.fragments.PerfilFragment
import com.example.feed_use.repository.RepositoryPostImp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private fun initDB() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDB()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frame_contaier) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)

        binding.buttonBack.visibility = View.GONE
        replaceFragment(FeedFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_feed -> {
                    binding.buttonBack.visibility = View.GONE
                    replaceFragment(FeedFragment())
                }
                R.id.nav_post -> {
                    val intent = Intent(this, NewPostActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_perfil -> {
                    binding.buttonBack.visibility = View.GONE
                    replaceFragment(PerfilFragment())
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigationView.selectedItemId = R.id.nav_feed
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_contaier, fragment)
            .addToBackStack("Fragment").commit()
        //supportFragmentManager.beginTransaction().replace(R.id.frame_contaier, fragment).commit()
    }

}