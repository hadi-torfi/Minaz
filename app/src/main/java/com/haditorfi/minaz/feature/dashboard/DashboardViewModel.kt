package com.haditorfi.minaz.feature.dashboard

import com.haditorfi.minaz.common.MyViewModel
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.product.Product
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.provide.ProvideService

class DashboardViewModel() : MyViewModel() {
    val customer: Customer = Customer()
    val product: Product = Product()
    val service: ProvideService = ProvideService()

}