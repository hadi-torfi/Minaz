package com.haditorfi.minaz.data.personnel

import androidx.lifecycle.LiveData

class PersonnelRepositoryImpl(private val personnelLocalDataSource: PersonnelDataSource) :
    PersonnelRepository {

    override val getAll: LiveData<List<Personnel>> = personnelLocalDataSource.getAll()

    override suspend fun insert(personnel: Personnel) = personnelLocalDataSource.insert(personnel)

    override suspend fun update(personnel: Personnel) = personnelLocalDataSource.update(personnel)

    override suspend fun delete(personnel: Personnel) = personnelLocalDataSource.delete(personnel)
}