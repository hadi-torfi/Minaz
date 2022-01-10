package com.haditorfi.minaz.feature.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.IViewHolder
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.databinding.ProductItemBinding

class ProductAdapter(
    val context: Context,
    private val values: List<Product>,
    var itemClickListener: (item: View, product: Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), IViewHolder {
        override fun setData(position: Int) {
            binding.myProduct = values[position]
        }

        override fun setItemClickListener(position: Int) {
            binding.btnMore.setOnClickListener {
                itemClickListener.invoke(it, values[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ProductItemBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.product_item,
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