package com.haditorfi.minaz.feature.staff

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.data.staff.StaffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StaffViewModel(private val staffRepository: StaffRepository) : ViewModel() {

    val allPersonnel = staffRepository.getAll

    fun insertStaff(vararg staff: Staff) {
        viewModelScope.launch(Dispatchers.IO) {
            staffRepository.insert(*staff)
        }
    }

    fun updateStaff(staff: Staff) {
        viewModelScope.launch(Dispatchers.IO) {
            staffRepository.update(staff)
        }
    }

    fun deleteStaff(staff: Staff) {
        viewModelScope.launch(Dispatchers.IO) {
            staffRepository.delete(staff)
        }
    }
}