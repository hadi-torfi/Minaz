package com.haditorfi.minaz.feature.services.provide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.ProvideServiceRepository
import com.haditorfi.minaz.data.service.provide.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProvideServiceViewModel(private val provideServiceRepository: ProvideServiceRepository) :
    ViewModel() {

    val allProvideService = provideServiceRepository.getAll

    fun insertProvideService(provideService: ProvideService) {
        viewModelScope.launch(Dispatchers.IO) {
            provideServiceRepository.insert(provideService)
        }
    }

    fun updateProvideService(provideService: ProvideService) {
        viewModelScope.launch(Dispatchers.IO) {
            provideServiceRepository.update(provideService)
        }
    }

    fun deleteProvideService(provideService: ProvideService) {
        viewModelScope.launch(Dispatchers.IO) {
            provideServiceRepository.delete(provideService)
        }
    }

}