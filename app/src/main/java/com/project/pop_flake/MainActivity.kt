package com.project.pop_flake

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.project.pop_flake.databinding.ActivityMainBinding
import com.project.pop_flake.ui.home.HomeFragment
import com.project.pop_flake.ui.search.SearchFragment
import com.project.pop_flake.ui.settings.SettingsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private  var  binding: ActivityMainBinding?=null
    private lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

         navController = findNavController(R.id.nav_fragment)
        binding!!.bottomNavigationView.setupWithNavController(navController)


    }


}