package com.haditorfi.minaz.feature.personnel

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.databinding.PersonnelItemBinding

class PersonnelAdapter(
    val context: Context,
    private val values: List<Personnel>,
    val IItemClickListener: (view: View, personnel: Personnel) -> Unit
) : RecyclerView.Adapter<PersonnelAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: PersonnelItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataPersonnel(personnel: Personnel) {
            binding.myPersonnel = personnel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: PersonnelItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.personnel_item, parent, false)
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