package com.haditorfi.minaz.feature.services.provide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.databinding.ServiceProvideItemBinding

class ProvideServiceAdapter(
    val context: Context,
    private val values: List<ProvideService>,
    var IItemClickListener: (
        view: View,
        provideService: ProvideService
    ) -> Unit
) : RecyclerView.Adapter<ProvideServiceAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ServiceProvideItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataService(provideService: ProvideService) {
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

        holder.binding.btnMore.setOnClickListener {
            IItemClickListener(it, values[position])
        }
    }

    override fun getItemCount(): Int = values.size
}