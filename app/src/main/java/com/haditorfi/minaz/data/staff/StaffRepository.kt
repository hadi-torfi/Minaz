package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData

interface StaffRepository {

    val getAll: LiveData<List<Staff>>

    fun insert(staff: Staff)

    fun update(staff: Staff)

    fun delete(staff: Staff)
}