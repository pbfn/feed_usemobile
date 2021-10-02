package com.example.feed_use

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.feed_use.databinding.ActivityMainBinding
import com.example.feed_use.fragments.FeedFragment
import com.example.feed_use.fragments.PerfilFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(FeedFragment())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_feed -> {
                    replaceFragment(FeedFragment())
                }
                R.id.nav_perfil -> {
                    replaceFragment(PerfilFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_contaier, fragment)
            .addToBackStack("Fragment").commit()
        //supportFragmentManager.beginTransaction().replace(R.id.frame_contaier, fragment).commit()
    }

    //TODO VERIFICAR COMO VOLTAR PARA O FRAGMENTO ANTERIOR E TROCAR O ÍCONE SELECIONADO.
    override fun onBackPressed() {
        val support = supportFragmentManager
        if (support.backStackEntryCount == 0) {
            support.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}