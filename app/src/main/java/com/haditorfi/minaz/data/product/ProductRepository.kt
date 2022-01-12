package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData

interface ProductRepository {

    val getAll: LiveData<List<Product>>

    fun insert(product: Product)

    fun delete(product: Product)

    fun update(product: Product)
}