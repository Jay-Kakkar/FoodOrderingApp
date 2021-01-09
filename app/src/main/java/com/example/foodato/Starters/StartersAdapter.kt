package com.example.foodato.Starters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.databinding.StartersListBinding

class StartersAdapter(
    private val data: ArrayList<StartersData>,
    val onClickListener: Clicklisteners
) :
    RecyclerView.Adapter<StartersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        Log.e(this.toString(), "!!!!!!!!!!!!!!!!!!!!!!!!${item}")
        holder.addItem.setOnClickListener {
            onClickListener.clickListener(item.getPriceInt(),item.getStarterName())
        }
        holder.bind(item)
    }

    class ViewHolder(var binding: StartersListBinding) : RecyclerView.ViewHolder(binding.root) {

        var mImageResourceId = binding.listarter
        var mPrice = binding.price
        var mName = binding.startername
        var addItem = binding.addItem
        private var startersData: StartersData? = null
        fun bind(item: StartersData) {

            mImageResourceId.setImageResource(item.getImaageResourceId())
            mName.text = item.getStarterName()
            mPrice.text = item.getPrice()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = StartersListBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class Clicklisteners(val clickListener: (price: Int,starterName:String) -> Unit) {
        fun onClick(starters:StartersData) {
            clickListener(starters.getPriceInt(),starters.getStarterName())
        }


    }
}