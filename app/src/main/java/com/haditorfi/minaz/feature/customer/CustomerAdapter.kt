package com.haditorfi.minaz.feature.customer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.IViewHolder
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.CustomerItemBinding

class CustomerAdapter(
    val context: Context,
    private val values: List<Customer>,
    var itemClickListener: (item: View, customer: Customer) -> Unit
) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root), IViewHolder {

        override fun setData(position: Int) {
            binding.myCustomer = values[position]
        }

        override fun setItemClickListener(position: Int) {
            binding.btnMore.setOnClickListener { item ->
                itemClickListener.invoke(item, values[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CustomerItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.customer_item,
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