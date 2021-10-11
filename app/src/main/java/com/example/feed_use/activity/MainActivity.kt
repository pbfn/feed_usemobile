package com.example.feed_use.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.feed_use.R
import com.example.feed_use.data.User

import com.example.feed_use.databinding.ActivityMainBinding
import com.example.feed_use.fragments.FeedFragment
import com.example.feed_use.fragments.PerfilFragment
import com.example.feed_use.repository.RepositoryUser
import com.example.feed_use.repository.RepositoryUserImp


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //addUser()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.frame_contaier) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)


        replaceFragment(FeedFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_feed -> {
                    replaceFragment(FeedFragment())
                }
                R.id.nav_post -> {
                    val intent = Intent(this, NewPostActivity::class.java)
                    startActivity(intent)
                    onStop()
                }
                R.id.nav_perfil -> {
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
    }

    private fun addUser() {
        val user: User = User(
            "1",
            "Terry Crews",
            "https://api.time.com/wp-content/uploads/2017/12/terry-crews-person-of-year-2017-time-magazine-2.jpg?w=800&quality=85",
            0
        )
        val repositoryUserImp = RepositoryUserImp()

        repositoryUserImp.addUser(user)
    }


}