package com.haditorfi.minaz.data.service

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ServiceLocalDataSource : ServiceDataSource {

    @Query("SELECT * FROM service")
    override fun getAll(): LiveData<List<Service>>

    @Insert
    override suspend fun insert(service: Service)

    @Delete
    override suspend fun delete(service: Service)

    @Update
    override suspend fun update(service: Service)
}