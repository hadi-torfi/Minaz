package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StaffRepositoryImpl(private val staffDao: StaffDao) :
    StaffRepository {

    override val getAll: LiveData<List<Staff>> = staffDao.getAll()

    override fun insert(staff: Staff) {
        CoroutineScope(IO).launch {
            staffDao.insert(staff)
        }
    }

    override fun update(staff: Staff) {
        CoroutineScope(IO).launch {
            staffDao.update(staff)
        }
    }

    override fun delete(staff: Staff) {
        CoroutineScope(IO).launch {
            staffDao.delete(staff)
        }
    }
}