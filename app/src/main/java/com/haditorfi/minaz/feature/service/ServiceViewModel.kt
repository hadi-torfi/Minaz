package com.haditorfi.minaz.feature.service

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.data.service.Service
import com.haditorfi.minaz.data.service.ServiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceViewModel(private val serviceRepository: ServiceRepository) : ViewModel() {

    val allService = serviceRepository.getAll

    fun insertService(service: Service) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceRepository.insert(service)
        }
    }

    fun updateService(service: Service) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceRepository.update(service)
        }
    }

    fun deleteService(service: Service) {
        viewModelScope.launch(Dispatchers.IO) {
            serviceRepository.delete(service)
        }
    }
}