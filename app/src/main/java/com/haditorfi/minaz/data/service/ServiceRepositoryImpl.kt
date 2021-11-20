package com.haditorfi.minaz.data.service

import androidx.lifecycle.LiveData

class ServiceRepositoryImpl(private val serviceLocalDataSource: ServiceLocalDataSource) :
    ServiceRepository {
    override val getAll: LiveData<List<Service>> = serviceLocalDataSource.getAll()

    override suspend fun insert(service: Service) = serviceLocalDataSource.insert(service)

    override suspend fun delete(service: Service) = serviceLocalDataSource.delete(service)

    override suspend fun update(service: Service) = serviceLocalDataSource.update(service)
}