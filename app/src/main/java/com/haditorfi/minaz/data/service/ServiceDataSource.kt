package com.haditorfi.minaz.data.service

import androidx.lifecycle.LiveData

interface ServiceDataSource {
    fun getAll(): LiveData<List<Service>>

    suspend fun insert(service: Service)

    suspend fun delete(service: Service)

    suspend fun update(service: Service)
}