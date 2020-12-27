package com.example.foodato.Starters

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodato.R
import com.example.foodato.databinding.FragmentStartersBinding
import com.example.foodato.databinding.StartersListBinding

class Starters : Fragment() {
    private lateinit var binding: FragmentStartersBinding
    private var data = ArrayList<StartersData>()
    private lateinit var adapter: StartersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_starters,container,false)
         adapter=StartersAdapter(data)

        binding.recycler.adapter=adapter

        return binding.root
    }



    override fun onStart() {
        super.onStart()
        if(data.size==0){
            initialiseStarters()
        }
    }

    private fun initialiseStarters():ArrayList<StartersData> {
        data.add(StartersData(R.drawable.paneertikka,150,"Paneer Tikka"))
        data.add(StartersData(R.drawable.chillypotato,140,"Chilly Potato"))
        data.add(StartersData(R.drawable.springroll,130,"Spring Roll"))
        data.add(StartersData(R.drawable.whitesauspasta,160,"White Saus Pasta"))
        data.add(StartersData(R.drawable.redssauspasta,150,"Red Saus Pasta"))
        data.add(StartersData(R.drawable.chowmein,120,"Chowmein"))
        data.add(StartersData(R.drawable.momos,130,"Momos"))
        data.add(StartersData(R.drawable.pizza,200,"Pizza"))
        return data
    }
}