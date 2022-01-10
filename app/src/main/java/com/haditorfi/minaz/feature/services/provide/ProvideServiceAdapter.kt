package com.haditorfi.minaz.feature.services.provide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideItemBinding

class ProvideServiceAdapter(
    private val context: Context,
    private val values: List<Provides>,
    private var itemClickListener: (item: View, provideService: Any) -> Unit
) : RecyclerView.Adapter<ProvideServiceAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ServiceProvideItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDataService(position: Int) {
            binding.myProvideService = values[position]
        }

        fun setOnClickListeners(position: Int) {
            binding.apply {
                itemServiceLayout.setOnClickListener { item ->
                    itemClickListener.invoke(item, values[position])
                }
                btnMore.setOnClickListener { item ->
                    itemClickListener.invoke(item, values[position].provideService)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ServiceProvideItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.service_provide_item,
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            setDataService(position)
            setOnClickListeners(position)
        }
    }

    override fun getItemCount(): Int = values.size
}