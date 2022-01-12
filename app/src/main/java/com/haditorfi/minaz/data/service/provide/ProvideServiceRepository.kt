package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData

interface ProvideServiceRepository {

    val getAll: LiveData<List<Provides>>

    fun insert(provideService: ProvideService)

    fun delete(provideService: ProvideService)

    fun update(provideService: ProvideService)


}