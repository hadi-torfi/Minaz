package com.haditorfi.minaz.data.service.service

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ServiceRepositoryImpl(private val serviceDao: ServiceDao) :
    ServiceRepository {
    override val getAll: LiveData<List<Service>> = serviceDao.getAll()

    override fun insert(service: Service) {
        CoroutineScope(IO).launch {
            serviceDao.insert(service)
        }
    }

    override fun delete(service: Service) {
        CoroutineScope(IO).launch {
            serviceDao.delete(service)
        }
    }

    override fun update(service: Service) {
        CoroutineScope(IO).launch {
            serviceDao.update(service)
        }
    }
}