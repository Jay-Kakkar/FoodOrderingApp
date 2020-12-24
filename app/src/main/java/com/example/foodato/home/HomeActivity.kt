package com.example.foodato.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodato.MainActivity
import com.example.foodato.R
import com.example.foodato.databinding.ActivityHomeBinding
import com.example.foodato.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityHomeBinding>(
            this,
            R.layout.activity_home
        )//        var button:Button=findViewById(R.id.out)
//        button.setOnClickListener {
//            val prefs = PreferenceManager.getDefaultSharedPreferences(this)
//            prefs.edit().putBoolean("Islogin", false).apply()
//            var intent=Intent(this,MainActivity::class.java)
//            startActivity(intent)
//        }

        drawerLayout = binding.drawerLayout
        var menuButton = binding.imageMenu
        menuButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        val navigationView = binding.navViewHome
        var navController = Navigation.findNavController(this, R.id.navHost)
        Log.e(
            this.toString(), "OOOOOOOOOOOOOOOOOOOOOOOOOOO${
                NavigationUI.setupWithNavController(navigationView, navController)
            }"
        )
        NavigationUI.setupWithNavController(navigationView, navController)

    }
}