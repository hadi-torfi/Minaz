package com.haditorfi.minaz.data.personnel

import androidx.lifecycle.LiveData

interface PersonnelDataSource {

    fun getAll(): LiveData<List<Personnel>>

    suspend fun insert(vararg personnel: Personnel)

    suspend fun update(personnel: Personnel)

    suspend fun delete(personnel: Personnel)
}