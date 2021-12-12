package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData

class ProvideServiceRepositoryImpl(private val provideServiceDao: ProvideServiceDao) :
    ProvideServiceRepository {

    override val getAll: LiveData<List<Provides>> = provideServiceDao.getAll()

    override suspend fun insert(provideService: ProvideService) =
        provideServiceDao.insert(provideService)

    override suspend fun delete(provideService: ProvideService) =
        provideServiceDao.delete(provideService)

    override suspend fun update(provideService: ProvideService) =
        provideServiceDao.update(provideService)

}