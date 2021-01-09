package com.example.foodato.Veg

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.Starters.StartersAdapter
import com.example.foodato.Starters.StartersData
import com.example.foodato.databinding.VegListBinding

class VegFoodAdapter(
    private val data: ArrayList<vegFoodData>, val onClickListener: Clicklisteners
) : RecyclerView.Adapter<VegFoodAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.e(this.toString(), "!!!!!!!!!!!!!!!!!!!!!!!!${item}")
        holder.addItem.setOnClickListener {
            onClickListener.clickListener(item.getPriceInt(),item.getVegFoodName())
        }
        holder.bind(item)
    }

    class ViewHolder(var binding: VegListBinding) : RecyclerView.ViewHolder(binding.root) {

        var mImageResourceId = binding.listVegFoodImage
        var mPrice = binding.price
        var mName = binding.vegname
        var addItem = binding.addItem
        fun bind(item: vegFoodData) {

            mImageResourceId.setImageResource(item.getImaageResourceId())
            mName.text = item.getVegFoodName()
            mPrice.text = item.getPrice()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = VegListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Clicklisteners(val clickListener: (price: Int, vegName: String) -> Unit) {
        fun onClick(veg: vegFoodData) {
            clickListener(veg.getPriceInt(), veg.getVegFoodName())
        }


    }
}