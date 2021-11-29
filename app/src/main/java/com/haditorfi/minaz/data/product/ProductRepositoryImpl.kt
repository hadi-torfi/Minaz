package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData

class ProductRepositoryImpl(private val productLocalDataSource: ProductDataSource) :
    ProductRepository {
    override val getAll: LiveData<List<Product>> = productLocalDataSource.getAll()

    override suspend fun insert(product: Product) = productLocalDataSource.insert(product)

    override suspend fun delete(product: Product) = productLocalDataSource.delete(product)

    override suspend fun update(product: Product) = productLocalDataSource.update(product)
}