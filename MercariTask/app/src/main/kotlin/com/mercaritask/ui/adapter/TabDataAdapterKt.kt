package com.mercaritask.ui.adapter

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.mercaritask.R
import com.mercaritask.data.model.TabDataKt
import com.mercaritask.databinding.ItemGridKtBinding

class TabDataAdapterKt(private val tabData: List<TabDataKt>) : RecyclerView.Adapter<TabDataAdapterKt.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemGridKtBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_grid_kt, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return tabData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.data = tabData[position]
    }

    inner class ViewHolder(var binding: ItemGridKtBinding) : RecyclerView.ViewHolder(binding.root)
}