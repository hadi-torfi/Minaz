package com.haditorfi.minaz.data.service.service

import androidx.lifecycle.LiveData

interface ServiceRepository {

    val getAll: LiveData<List<Service>>

    fun insert(service: Service)

    fun delete(service: Service)

    fun update(service: Service)
}