package com.haditorfi.minaz.data.personnel

import androidx.lifecycle.LiveData

interface PersonnelRepository {

    val getAll: LiveData<List<Personnel>>

    suspend fun insert(personnel: Personnel)

    suspend fun update(personnel: Personnel)

    suspend fun delete(personnel: Personnel)
}