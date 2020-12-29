package com.example.foodato.NonVeg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.foodato.R
import com.example.foodato.Veg.VegFoodAdapter
import com.example.foodato.Veg.vegFoodData
import com.example.foodato.databinding.FragmentNonVegFoodBinding
import com.example.foodato.databinding.FragmentVegFoodBinding

class NonVegFood : Fragment() {
    private lateinit var binding: FragmentNonVegFoodBinding
    private var data = ArrayList<NonVegData>()
    private lateinit var adapter: NonVegAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_non_veg_food,container,false)
        adapter= NonVegAdapter(data)

        binding.recycler.adapter=adapter

        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseNonVegFood()
        }
    }

    private fun initialiseNonVegFood(): ArrayList<NonVegData> {
        data.add(NonVegData(R.drawable.butterchicken,350,"Butter Chicken"))
        data.add(NonVegData(R.drawable.chickenbiryani,370,"Chicken Biryani"))
        data.add(NonVegData(R.drawable.chickenfries,400,"Chicken Fries"))
        data.add(NonVegData(R.drawable.chickenroll,320,"Chicken Roll"))
        data.add(NonVegData(R.drawable.eggtoast,330,"Egg Toast"))
        data.add(NonVegData(R.drawable.fishtikka,400,"Fish Tikka"))
        data.add(NonVegData(R.drawable.prawns,350,"Prawns"))
        data.add(NonVegData(R.drawable.tandoorichicken,380,"Tandoori Chicken"))
        return data
    }
}