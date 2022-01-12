package com.haditorfi.minaz.feature.services.service

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.service.service.Service
import com.haditorfi.minaz.data.service.service.ServiceRepository

class ServiceViewModel(private val serviceRepository: ServiceRepository) : ViewModel() {

    val allService = serviceRepository.getAll

    fun insertService(service: Service) = serviceRepository.insert(service)

    fun updateService(service: Service) = serviceRepository.update(service)

    fun deleteService(service: Service) = serviceRepository.delete(service)
}