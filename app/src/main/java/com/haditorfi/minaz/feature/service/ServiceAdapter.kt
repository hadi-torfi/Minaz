package com.haditorfi.minaz.feature.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ServiceItemBinding

class ServiceAdapter(
    val context: Context,
    private val values: List<Service>,
    var IItemClickListener: (
        view: View,
        service: Service
    ) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ServiceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataService(service: Service) {
            binding.myService = service
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ServiceItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.service_item, parent, false)
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