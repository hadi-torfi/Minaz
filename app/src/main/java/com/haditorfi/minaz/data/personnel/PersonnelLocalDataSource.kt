package com.haditorfi.minaz.data.personnel

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PersonnelLocalDataSource : PersonnelDataSource {

    @Query("SELECT * FROM personnel")
    override fun getAll(): LiveData<List<Personnel>>

    @Insert
    override suspend fun insert(personnel: Personnel)

    @Update
    override suspend fun update(personnel: Personnel)

    @Delete
    override suspend fun delete(personnel: Personnel)
}