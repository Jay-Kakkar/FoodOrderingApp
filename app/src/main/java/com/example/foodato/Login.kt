package com.example.foodato

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.foodato.databinding.FragmentLoginBinding


class Login : Fragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
//        loginBinding.imageView2.setBlur(4)
        loginBinding.signIn.setOnClickListener {
            findNavController().navigate(LoginDirections.actionLoginToSignIn2())
        }
        loginBinding.signUp.setOnClickListener {
            findNavController().navigate(LoginDirections.actionLoginToSignUp2())
        }
        return loginBinding.root
    }


}