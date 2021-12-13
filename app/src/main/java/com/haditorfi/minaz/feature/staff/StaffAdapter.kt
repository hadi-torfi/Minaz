package com.haditorfi.minaz.feature.staff

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.databinding.StaffItemBinding


class StaffAdapter(
    val context: Context,
    private val values: List<Staff>,
    val IItemClickListener: (view: View, staff: Staff) -> Unit
) : RecyclerView.Adapter<StaffAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: StaffItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataPersonnel(staff: Staff) {
            binding.myStaff = staff
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: StaffItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.staff_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataPersonnel(values[position])

        holder.binding.btnMore.setOnClickListener {
            IItemClickListener(it, values[position])
        }
    }

    override fun getItemCount(): Int = values.size
}