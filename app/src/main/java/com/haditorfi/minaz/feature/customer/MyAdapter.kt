package com.haditorfi.minaz.feature.customer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.ItemCustomerBinding

class MyAdapter(val context: Context, private val values: List<Customer>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemCustomerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataCustomer(customer: Customer) {
            binding.myCustomer = customer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ItemCustomerBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_customer, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataCustomer(values[position])
    }

    override fun getItemCount(): Int = values.size
}