package com.haditorfi.minaz.feature.customer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.DATA_KEY
import com.haditorfi.minaz.data.customer.Customer

class CustomerAdapter(private val values: List<Customer>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {
    class ViewHolder(val item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_customer, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.item.findViewById<TextView>(R.id.txt_customer_name).text =
            values[position].name
        holder.item.findViewById<TextView>(R.id.txt_customer_mobile).text =
            values[position].mobile

        holder.item.setOnClickListener {
            val bundle =
                bundleOf(
                    DATA_KEY to values[position]
                )

            holder.item.findNavController().navigate(
                R.id.action_customer_to_editCustomer,
                bundle
            )
        }
    }

    override fun getItemCount(): Int = values.size
}