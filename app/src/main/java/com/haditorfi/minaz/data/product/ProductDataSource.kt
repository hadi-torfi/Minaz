package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData

interface ProductDataSource {
    fun getAll(): LiveData<List<Product>>

    suspend fun insert(vararg product: Product)

    suspend fun delete(product: Product)

    suspend fun update(product: Product)
}