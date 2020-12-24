/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.foodato

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.foodato.databinding.ActivityMainBinding
import com.example.foodato.home.HomeActivity
import com.example.foodato.home.HomeScreen


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
//        drawerLayout = binding.drawerLayout
//        var menuButton = binding.imageMenu
//        menuButton.setOnClickListener {
//            drawerLayout.openDrawer(GravityCompat.START)
//        }
//        var navigationView = binding.navView
//        //for colorfull icons
//        navigationView.itemIconTintList = null
//
//        var navController = Navigation.findNavController(this, R.id.navHost)
//        NavigationUI.setupWithNavController(navigationView, navController)
    }

    override fun onStart() {
        super.onStart()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val Islogin = prefs.getBoolean("Islogin", false)
        if (Islogin) {   // condition true means user is already login
            Log.e(this.toString(),"HHHHHHHHHHHHHHHHHHHHH")
            var intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}
