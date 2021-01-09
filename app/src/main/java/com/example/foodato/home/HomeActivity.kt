package com.example.foodato.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.foodato.CartData
import com.example.foodato.MainActivity
import com.example.foodato.R
import com.example.foodato.SignOutDirections
import com.example.foodato.databinding.ActivityHomeBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_home.*


@Suppress("DEPRECATION")
class HomeActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mHeaderView: View
    private var cartData = ArrayList<CartData>()
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

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val usernamePref = prefs.getString("username", "")
        //setting value in nav header
        val navigationView = binding.navViewHome
        mHeaderView = navigationView.getHeaderView(0);
        var textViewUsername = mHeaderView.findViewById<View>(R.id.usernameHello) as TextView
//        Log.e(this.toString(),"hhhhhhhhhhhhhhhh${usernamePref}")
        textViewUsername.setText("Hello,$usernamePref");

        var navController = Navigation.findNavController(this, R.id.navHost)
        Log.e(
            this.toString(), "OOOOOOOOOOOOOOOOOOOOOOOOOOO${
                NavigationUI.setupWithNavController(navigationView, navController)
            }"
        )
        NavigationUI.setupWithNavController(navigationView, navController)

    }

    override fun onStart() {
        super.onStart()
        val appSharedPrefs = PreferenceManager
            .getDefaultSharedPreferences(this)
        val prefsEditor = appSharedPrefs.edit()
        cartData.clear()
        val gson = Gson()
        val json: String = gson.toJson(cartData)
        prefsEditor.putString("Details", json)
        prefsEditor.apply()
    }


}