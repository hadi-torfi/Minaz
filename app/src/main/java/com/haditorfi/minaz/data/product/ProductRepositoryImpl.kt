package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData

class ProductRepositoryImpl(private val productDao: ProductDao) :
    ProductRepository {
    override val getAll: LiveData<List<Product>> = productDao.getAll()

    override suspend fun insert(vararg product: Product) = productDao.insert(*product)

    override suspend fun delete(product: Product) = productDao.delete(product)

    override suspend fun update(product: Product) = productDao.update(product)
}