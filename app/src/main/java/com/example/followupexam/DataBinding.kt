package com.example.followupexam

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.followupexam.adapter.RecycleViewAdapter
import com.example.followupexam.model.Module

/**
 * This method to submit the module list to the recycle view adapter
 * @param recyclerView : to get the adapter
 * @param modules: the list that were going to submit
 */
@BindingAdapter("listData")
fun binRecycleView(recyclerView: RecyclerView,modules:List<Module>?){
    val adapter = recyclerView.adapter as RecycleViewAdapter
    adapter.submitList(modules)
}

