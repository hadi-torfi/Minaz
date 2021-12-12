package com.haditorfi.minaz.data.service.provide

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProvideServiceDao {

    @Transaction
    @Query("SELECT * FROM provideService")
    fun getAll(): LiveData<List<Provides>>

    @Insert
    suspend fun insert(provideService: ProvideService)

    @Delete
    suspend fun delete(provideService: ProvideService)

    @Update
    suspend fun update(provideService: ProvideService)

}