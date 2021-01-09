package com.example.foodato.Dessert

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.NonVeg.NonVegData
import com.example.foodato.Veg.VegFoodAdapter
import com.example.foodato.Veg.vegFoodData
import com.example.foodato.databinding.DessertListBinding
import com.example.foodato.databinding.VegListBinding

class DessertAdapter(
    private val data: ArrayList<DessertData>,
    val onClickListener: Clicklisteners
) : RecyclerView.Adapter<DessertAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.e(this.toString(), "!!!!!!!!!!!!!!!!!!!!!!!!${item}")
        holder.addItem.setOnClickListener {
            onClickListener.clickListener(item.getPriceInt(),item.getDessertName())
        }
        holder.bind(item)
    }

    class ViewHolder(var binding: DessertListBinding) : RecyclerView.ViewHolder(binding.root) {

        var mImageResourceId = binding.dessertImage
        var mPrice = binding.price
        var mName = binding.dessertname
        var addItem = binding.addItem
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

    class Clicklisteners(val clickListener: (price: Int, starterName: String) -> Unit) {
        fun onClick(dessert: DessertData) {
            clickListener(dessert.getPriceInt(), dessert.getDessertName())
        }
    }
}