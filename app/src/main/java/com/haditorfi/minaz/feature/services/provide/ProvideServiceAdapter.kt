package com.haditorfi.minaz.feature.services.provide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.Provides
import com.haditorfi.minaz.databinding.ServiceProvideItemBinding

class ProvideServiceAdapter(
    val context: Context,
    private val values: List<Provides>,
    var IItemClickListener: ((view: View, provideService: Any) -> Unit?)? = null
) : RecyclerView.Adapter<ProvideServiceAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ServiceProvideItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataService(provideService: Provides) {
            binding.myProvideService = provideService
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ServiceProvideItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.service_provide_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataService(values[position])
        holder.binding.itemServiceLayout.setOnClickListener {
            IItemClickListener?.let { listener -> listener(it, values[position]) }
        }
        holder.binding.btnMore.setOnClickListener {
            IItemClickListener?.let { listener -> listener(it, values[position].provideService) }
        }
    }

    override fun getItemCount(): Int = values.size
}