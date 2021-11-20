package com.haditorfi.minaz.feature.customer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.databinding.CustomerItemBinding

class CustomerAdapter(
    val context: Context,
    private val values: List<Customer>,
    var IItemClickListener: (
        view: View,
        customer: Customer
    ) -> Unit
) :
    RecyclerView.Adapter<CustomerAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: CustomerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataCustomer(customer: Customer) {
            binding.myCustomer = customer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: CustomerItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.customer_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataCustomer(values[position])

        holder.binding.btnMore.setOnClickListener {
            IItemClickListener(it, values[position])
        }
    }

    override fun getItemCount(): Int = values.size
}