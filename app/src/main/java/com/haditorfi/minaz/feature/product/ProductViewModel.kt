package com.haditorfi.minaz.feature.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.product.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepository: ProductRepository) : ViewModel() {

    val allProduct = productRepository.getAll

    fun insertProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.insert(product)
        }
    }

    fun updateProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.update(product)
        }
    }

    fun deleteProduct(product: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepository.delete(product)
        }
    }
}