package com.haditorfi.minaz.feature.services.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.IViewHolder
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceItemBinding

class ServiceAdapter(
    val context: Context,
    private val values: List<Service>,
    var itemClickListener: (item: View, service: Service) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ServiceItemBinding) :
        RecyclerView.ViewHolder(binding.root), IViewHolder {
        override fun setData(position: Int) {
            binding.myService = values[position]
        }

        override fun setItemClickListener(position: Int) {
            binding.btnMore.setOnClickListener { item ->
                itemClickListener.invoke(item, values[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ServiceItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.service_item,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            setData(position)
            setItemClickListener(position)
        }
    }

    override fun getItemCount(): Int = values.size
}