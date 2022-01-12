package com.haditorfi.minaz.data.service.service

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ServiceLocalDataSource {

    @Query("SELECT * FROM service")
    fun getAll(): LiveData<List<Service>>

    @Insert
    fun insert(vararg service: Service)

    @Delete
    fun delete(service: Service)

    @Update
    fun update(service: Service)
}