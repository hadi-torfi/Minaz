package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StaffDao {

    @Transaction
    @Query("SELECT * FROM staffs")
    fun getAll(): LiveData<List<Staff>>

    @Insert
    suspend fun insert(staff: Staff)

    @Update
    suspend fun update(staff: Staff)

    @Delete
    suspend fun delete(staff: Staff)
}