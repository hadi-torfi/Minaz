package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData

interface ProductRepository {

    val getAll: LiveData<List<Product>>

    suspend fun insert(product: Product)

    suspend fun delete(product: Product)

    suspend fun update(product: Product)
}