package com.example.foodato

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.findNavController


@Suppress("DEPRECATION")
class SignOut : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var alertDialog=AlertDialog.Builder(context)
        alertDialog.setMessage("Do you want to sign out")
        alertDialog.setNegativeButton("Sign Out",DialogInterface.OnClickListener{dialog, which ->

            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            prefs.edit().putBoolean("Islogin", false).apply()
            var intent=Intent(activity,MainActivity::class.java)
            startActivity(intent)
        })
        alertDialog.setPositiveButton("Cancel",DialogInterface.OnClickListener{
            dialog, which ->
findNavController().navigate(SignOutDirections.actionNavSignOutToNavHome())
        })
        val alertDialogBuilder: AlertDialog = alertDialog.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_out, container, false)
    }

}