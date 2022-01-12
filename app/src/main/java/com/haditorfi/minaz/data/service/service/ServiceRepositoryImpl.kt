package com.haditorfi.minaz.data.service.service

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ServiceRepositoryImpl(private val serviceLocalDataSource: ServiceLocalDataSource) :
    ServiceRepository {
    override val getAll: LiveData<List<Service>> = serviceLocalDataSource.getAll()

    override fun insert(service: Service) {
        CoroutineScope(IO).launch {
            serviceLocalDataSource.insert(service)
        }
    }

    override fun delete(service: Service) {
        CoroutineScope(IO).launch {
            serviceLocalDataSource.delete(service)
        }
    }

    override fun update(service: Service) {
        CoroutineScope(IO).launch {
            serviceLocalDataSource.update(service)
        }
    }
}