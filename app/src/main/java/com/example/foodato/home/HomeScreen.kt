package com.example.foodato.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodato.R
import com.example.foodato.databinding.FragmentHomeScreenBinding
import kotlinx.android.synthetic.main.fragment_login.*

class HomeScreen : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                var alertDialog = AlertDialog.Builder(context)
                alertDialog.setMessage("Do you want to exit")
                alertDialog.setNegativeButton(
                    "yes",
                    DialogInterface.OnClickListener { dialog, which ->
                        activity?.finishAffinity()
                        activity!!.finish()

                    })
                alertDialog.setPositiveButton(
                    "no",
                    DialogInterface.OnClickListener { dialog, which ->
                    })
                val alertDialogBuilder: AlertDialog = alertDialog.create()
                alertDialog.setCancelable(false)
                alertDialog.show()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen, container, false)

        binding.starter.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionNavHomeToStarters())
        }
        binding.dessert.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionNavHomeToDessert2())
        }
        binding.nonveg.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionNavHomeToNonVegFood())

        }
        binding.veg.setOnClickListener {
            findNavController().navigate(HomeScreenDirections.actionNavHomeToVegFood())
        }
        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_screen_menu, menu)
    }
}