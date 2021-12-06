package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData

class ProvideServiceRepositoryImpl(private val provideServiceLocalDataSource: ProvideServiceDataSource) :
    ProvideServiceRepository {
    override val getAll: LiveData<List<ProvideService>> = provideServiceLocalDataSource.getAll()

    override suspend fun insert(provideService: ProvideService) =
        provideServiceLocalDataSource.insert(provideService)

    override suspend fun delete(provideService: ProvideService) =
        provideServiceLocalDataSource.delete(provideService)

    override suspend fun update(provideService: ProvideService) =
        provideServiceLocalDataSource.update(provideService)
}