package com.haditorfi.minaz.feature.product

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.product.ProductRepository

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val allProduct = productRepository.getAll

    fun insertProduct(product: Product) = productRepository.insert(product)

    fun updateProduct(product: Product) = productRepository.update(product)

    fun deleteProduct(product: Product) = productRepository.delete(product)
}