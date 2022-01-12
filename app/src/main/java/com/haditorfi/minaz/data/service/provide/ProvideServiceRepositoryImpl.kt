package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProvideServiceRepositoryImpl(private val provideServiceDao: ProvideServiceDao) :
    ProvideServiceRepository {

    override val getAll: LiveData<List<Provides>> = provideServiceDao.getAll()

    override fun insert(provideService: ProvideService) {
        CoroutineScope(Dispatchers.IO).launch {
            provideServiceDao.insert(provideService)
        }
    }

    override fun delete(provideService: ProvideService) {
        CoroutineScope(Dispatchers.IO).launch {
            provideServiceDao.delete(provideService)
        }
    }

    override fun update(provideService: ProvideService) {
        CoroutineScope(Dispatchers.IO).launch {
            provideServiceDao.update(provideService)
        }
    }
}