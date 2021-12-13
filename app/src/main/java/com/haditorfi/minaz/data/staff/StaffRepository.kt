package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData

interface StaffRepository {

    val getAll: LiveData<List<Staff>>

    suspend fun insert(vararg staff: Staff)

    suspend fun update(staff: Staff)

    suspend fun delete(staff: Staff)
}