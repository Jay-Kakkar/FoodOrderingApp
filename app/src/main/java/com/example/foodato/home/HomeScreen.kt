package com.example.foodato.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.foodato.R
import com.example.foodato.databinding.FragmentHomeScreenBinding
import kotlinx.android.synthetic.main.fragment_login.*

class HomeScreen : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
private lateinit var binding: FragmentHomeScreenBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home_screen, container, false)
    binding=DataBindingUtil.inflate(inflater,R.layout.fragment_home_screen,container,false)
        val textView: TextView =binding.home
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}