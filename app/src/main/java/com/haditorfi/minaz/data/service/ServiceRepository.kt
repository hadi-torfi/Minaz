package com.haditorfi.minaz.data.service

import androidx.lifecycle.LiveData

interface ServiceRepository {

    val getAll: LiveData<List<Service>>

    suspend fun insert(vararg service: Service)

    suspend fun delete(service: Service)

    suspend fun update(service: Service)
}