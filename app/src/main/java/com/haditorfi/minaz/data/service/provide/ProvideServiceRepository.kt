package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData

interface ProvideServiceRepository {

    val getAll: LiveData<List<Provides>>

    suspend fun insert(provideService: ProvideService)

    suspend fun delete(provideService: ProvideService)

    suspend fun update(provideService: ProvideService)


}