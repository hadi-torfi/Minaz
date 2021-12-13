package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData

class StaffRepositoryImpl(private val staffDao: StaffDao) :
    StaffRepository {

    override val getAll: LiveData<List<Staff>> = staffDao.getAll()

    override suspend fun insert(vararg staff: Staff) = staffDao.insert(*staff)

    override suspend fun update(staff: Staff) = staffDao.update(staff)

    override suspend fun delete(staff: Staff) = staffDao.delete(staff)
}