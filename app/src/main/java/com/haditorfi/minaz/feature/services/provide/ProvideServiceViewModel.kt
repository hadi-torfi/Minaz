package com.haditorfi.minaz.feature.services.provide

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.service.provide.ProvideService
import com.haditorfi.minaz.data.service.provide.ProvideServiceRepository

class ProvideServiceViewModel(private val provideServiceRepository: ProvideServiceRepository) :
    ViewModel() {

    val allProvideService = provideServiceRepository.getAll

    fun insertProvideService(provideService: ProvideService) =
        provideServiceRepository.insert(provideService)

    fun updateProvideService(provideService: ProvideService) =
        provideServiceRepository.update(provideService)

    fun deleteProvideService(provideService: ProvideService) =
        provideServiceRepository.delete(provideService)

}