package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProvideServiceLocalDataSource : ProvideServiceDataSource {

    @Query("SELECT * FROM provideService")
    override fun getAll(): LiveData<List<ProvideService>>

    @Insert
    override suspend fun insert(provideService: ProvideService)

    @Delete
    override suspend fun delete(provideService: ProvideService)

    @Update
    override suspend fun update(provideService: ProvideService)
}