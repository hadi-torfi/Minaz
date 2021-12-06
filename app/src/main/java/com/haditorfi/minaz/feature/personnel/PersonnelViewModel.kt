package com.haditorfi.minaz.feature.personnel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haditorfi.minaz.data.personnel.Personnel
import com.haditorfi.minaz.data.personnel.PersonnelRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonnelViewModel(private val personnelRepository: PersonnelRepository) : ViewModel() {

    val allPersonnel = personnelRepository.getAll

    fun insertPersonnel(vararg personnel: Personnel) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelRepository.insert(*personnel)
        }
    }

    fun updatePersonnel(personnel: Personnel) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelRepository.update(personnel)
        }
    }

    fun deletePersonnel(personnel: Personnel) {
        viewModelScope.launch(Dispatchers.IO) {
            personnelRepository.delete(personnel)
        }
    }
}