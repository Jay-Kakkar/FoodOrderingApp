package com.example.foodato.viewcart

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.foodato.databinding.ViewCartLsitBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.foodato.Starters.StartersData


class ViewCartAdapter(
    val onClickListener: Clicklisteners
) :
//    RecyclerView.Adapter<ViewCartAdapter.ViewHolder>() {
//Earlier used problem came in imports
        ListAdapter<CartData,ViewCartAdapter.ViewHolder>(CartDiffUtilCallBacks()) {

//    var data = listOf<CartData>()
//            set(value) {
//            field = value
////            notifyDataSetChanged()
//        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val item = data[position]
        val item=getItem(position)
Log.e(this.toString(),"PPPPPPPPPPPPPPPP$item")
        holder.mRemove.setOnClickListener {
            onClickListener.clickListener(item.getPriceInt(), item.getDishName())

        }
        holder.bind(item)
    }

    class ViewHolder(var binding: ViewCartLsitBinding) : RecyclerView.ViewHolder(binding.root) {
        var mRemove = binding.remove
        var mPrice = binding.itemPrice
        var mName = binding.itemName
        fun bind(item: CartData) {

            mName.text = item.getDishName()
            mPrice.text = item.getPriceInt().toString()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewCartLsitBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

//    override fun getItemCount(): Int {
//        return data.size
//    }

    class Clicklisteners(val clickListener: (price: Int, starterName: String) -> Unit) {

        fun onClick(cartData: CartData) {
            clickListener(cartData.getPriceInt(),cartData.getDishName())
        }

    }

    class CartDiffUtilCallBacks : DiffUtil.ItemCallback<CartData>() {

        override fun areItemsTheSame(oldItem: CartData, newItem: CartData): Boolean {
            return oldItem== newItem
        }


        override fun areContentsTheSame(oldItem: CartData, newItem: CartData): Boolean {
            return oldItem.getDishName() == newItem.getDishName()
        }
    }

}