package com.example.foodato.Dessert

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.Veg.VegFoodAdapter
import com.example.foodato.Veg.vegFoodData
import com.example.foodato.databinding.DessertListBinding
import com.example.foodato.databinding.VegListBinding

class DessertAdapter (private val data:ArrayList<DessertData>) : RecyclerView.Adapter<DessertAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.e(this.toString(),"!!!!!!!!!!!!!!!!!!!!!!!!${item}")

        holder.bind(item)
    }

    class ViewHolder(var binding: DessertListBinding) : RecyclerView.ViewHolder(binding.root) {

        var mImageResourceId = binding.dessertImage
        var mPrice = binding.price
        var mName = binding.dessertname
        fun bind(item: DessertData) {

            mImageResourceId.setImageResource(item.getImaageResourceId())
            mName.text = item.getDessertName()
            mPrice.text = item.getPrice()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DessertListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}