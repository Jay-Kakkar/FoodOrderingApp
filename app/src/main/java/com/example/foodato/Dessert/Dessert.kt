package com.example.foodato.Dessert

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.foodato.R
import com.example.foodato.databinding.FragmentDessertBinding


class Dessert :Fragment() {
    private lateinit var binding: FragmentDessertBinding
    private var data = ArrayList<DessertData>()
    private lateinit var adapter: DessertAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_dessert,container,false)
        adapter= DessertAdapter(data)

        binding.recycler.adapter=adapter

        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseDesserts()
        }
    }

    private fun initialiseDesserts(): ArrayList<DessertData> {
        data.add(DessertData(R.drawable.berrycreamcustard,200,"Berry Cream Custard"))
        data.add(DessertData(R.drawable.brownie,170,"Chocolate Brownie"))
        data.add(DessertData(R.drawable.chocolatemouse,160,"Chocolate Mouse"))
        data.add(DessertData(R.drawable.cupcake,200,"Cup Cake"))
        data.add(DessertData(R.drawable.frenchmacaroons,220,"Macarons"))
        data.add(DessertData(R.drawable.icecream,190,"Ice Cream"))
        data.add(DessertData(R.drawable.donuts,150,"Donuts"))
        data.add(DessertData(R.drawable.cheesecake,200,"Cheese Cake"))
        return data
    }
}