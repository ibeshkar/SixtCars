package com.example.sixtcars.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.sixtcars.data.entity.Car
import com.example.sixtcars.databinding.ViewCarListRowBinding

class CarListAdapter : RecyclerView.Adapter<CarListAdapter.ItemViewHolder>() {

    private var itemList: ArrayList<Car> = arrayListOf()

    fun setItems(newList: ArrayList<Car>) {
        val diffCallback = ItemDiffCallback(this.itemList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.itemList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun getItem(position: Int): Car {
        return this.itemList[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewCarListRowBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.car = item
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ItemViewHolder(val binding: ViewCarListRowBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class ItemDiffCallback(
        private val mOldList: List<Car>,
        private val mNewList: List<Car>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return mOldList.size
        }

        override fun getNewListSize(): Int {
            return mNewList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return mOldList[oldItemPosition].id == mNewList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = mOldList[oldItemPosition]
            val newItem = mNewList[newItemPosition]
            return (oldItem == newItem)
        }

    }
}