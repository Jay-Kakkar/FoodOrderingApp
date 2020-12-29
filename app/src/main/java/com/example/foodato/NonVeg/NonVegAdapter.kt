package com.example.foodato.NonVeg

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.databinding.NonvegListBinding
import com.example.foodato.databinding.VegListBinding

class NonVegAdapter(private val data:ArrayList<NonVegData>) : RecyclerView.Adapter<NonVegAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.e(this.toString(),"!!!!!!!!!!!!!!!!!!!!!!!!${item}")

        holder.bind(item)
    }

    class ViewHolder(var binding:  NonvegListBinding) : RecyclerView.ViewHolder(binding.root) {

        var mImageResourceId = binding.listNonVegFoodImage
        var mPrice = binding.price
        var mName = binding.nonvegname
        fun bind(item: NonVegData) {

            mImageResourceId.setImageResource(item.getImaageResourceId())
            mName.text = item.getNonVegFoodName()
            mPrice.text = item.getPrice()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NonvegListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}