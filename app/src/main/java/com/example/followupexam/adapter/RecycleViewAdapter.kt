package com.example.followupexam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.followupexam.adapter.RecycleViewAdapter.OnClickListener
import com.example.followupexam.databinding.ListItemBinding
import com.example.followupexam.model.Module

/**
 * This class is an adapter of RecycleView for modules list that is going to display on [SemesterFragment]
 * @constructor tack a value of type [OnClickListener] a nested class of this class responsible of click event
 */
class RecycleViewAdapter(val onClickListener: OnClickListener): ListAdapter<Module,
        RecycleViewAdapter.ListItemViewHolder>(DiffCallback) {

    /**
     * This class return The view of each list item
     * and bind data with List_item layout element
     */
    class ListItemViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(module: Module){
            binding.module = module
            binding.executePendingBindings()
        }
    }

    /**
     * This method create [ListItemViewHolder] for recycle view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        return ListItemViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    /**
     * This method responsible for bind data with [ListItemViewHolder] instance
     */
    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        //Get the clicked  item data
        val data = getItem(position)
        holder.itemView.setOnClickListener {
            //If use click get the corresponding data
            onClickListener.onClick(data)
        }
        holder.bind(data)
    }

    /**
     * These tow method responsible of keep track of data changes
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Module>(){
        override fun areItemsTheSame(oldItem: Module, newItem: Module): Boolean {
            return oldItem.idModule == newItem.idModule
        }

        override fun areContentsTheSame(oldItem: Module, newItem: Module): Boolean {
            return oldItem == newItem
        }

    }

    class OnClickListener(val clickListener:(module: Module) -> Unit){
        fun onClick(module: Module) = clickListener(module)
    }
}