package com.haditorfi.minaz.feature.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.haditorfi.minaz.R
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.databinding.ProductItemBinding
import com.haditorfi.minaz.databinding.ServiceItemBinding

class ProductAdapter(
    val context: Context,
    private val values: List<Product>,
    var IItemClickListener: (
        view: View,
        product: Product
    ) -> Unit
) : RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setDataProduct(product: Product) {
            binding.myProduct = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding: ProductItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.product_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setDataProduct(values[position])

        holder.binding.btnMore.setOnClickListener {
            IItemClickListener(it, values[position])
        }
    }

    override fun getItemCount(): Int = values.size
}