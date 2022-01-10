package com.haditorfi.minaz.feature.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.IViewHolder
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.databinding.StaffItemBinding


class StaffAdapter(
    val context: Context,
    private val values: List<Staff>,
    val ItemClickListener: ((item: View, staff: Staff) -> Unit?)? = null
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: StaffItemBinding) :
        RecyclerView.ViewHolder(binding.root), IViewHolder {

        override fun setData(position: Int) {
            binding.myStaff = values[position]
        }

        override fun setItemOnClickListener(position: Int) {
            binding.btnMore.setOnClickListener { item ->
                ItemClickListener?.let { listener ->
                    listener(item, values[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: StaffItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.staff_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            setData(position)
            setItemOnClickListener(position)
        }
    }

    override fun getItemCount(): Int = values.size
}