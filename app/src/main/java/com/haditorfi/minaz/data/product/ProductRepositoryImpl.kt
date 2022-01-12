package com.haditorfi.minaz.data.product

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRepositoryImpl(private val productDao: ProductDao) :
    ProductRepository {
    override val getAll: LiveData<List<Product>> = productDao.getAll()

    override fun insert(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.insert(product)
        }
    }

    override fun delete(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.delete(product)
        }
    }

    override fun update(product: Product) {
        CoroutineScope(Dispatchers.IO).launch {
            productDao.update(product)
        }
    }
}