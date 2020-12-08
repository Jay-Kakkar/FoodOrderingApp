package com.example.foodato

import android.annotation.SuppressLint
import android.database.DatabaseUtils
import android.graphics.Color
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.foodato.databinding.StartedBinding

class getStarted : Fragment() {

    private lateinit var startedBinding: StartedBinding
    @SuppressLint("ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        startedBinding = DataBindingUtil.inflate(inflater, R.layout.started, container, false)
        startedBinding.imageView2.setBlur(4)
        startedBinding.button.setOnClickListener {
            findNavController().navigate(getStartedDirections.actionGetStartedToLogin())
        }
        return startedBinding.root
    }
}