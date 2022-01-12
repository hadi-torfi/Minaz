package com.haditorfi.minaz.feature.staff

import androidx.lifecycle.ViewModel
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.data.staff.StaffRepository

class StaffViewModel(private val staffRepository: StaffRepository) : ViewModel() {

    val staffsLiveData = staffRepository.getAll

    fun insertStaff(staff: Staff) = staffRepository.insert(staff)

    fun updateStaff(staff: Staff) = staffRepository.update(staff)

    fun deleteStaff(staff: Staff) = staffRepository.delete(staff)
}