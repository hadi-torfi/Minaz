package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData

interface ProvideServiceDataSource {
    fun getAll(): LiveData<List<ProvideService>>

    suspend fun insert(provideService: ProvideService)

    suspend fun delete(provideService: ProvideService)

    suspend fun update(provideService: ProvideService)
}