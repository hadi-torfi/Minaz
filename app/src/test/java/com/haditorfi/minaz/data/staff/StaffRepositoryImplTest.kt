package com.haditorfi.minaz.data.staff

import androidx.lifecycle.LiveData
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StaffRepositoryImplTest {
    lateinit var staffRepositoryImpl: StaffRepositoryImpl
    lateinit var staffRepository: StaffRepository

    @Mock
    lateinit var staffDao: StaffDao

    @Before
    fun setUp() {
        staffRepositoryImpl = StaffRepositoryImpl(staffDao)
    }

    @Test
    fun getGetAll() {
        val all: LiveData<List<Staff>> = staffRepositoryImpl.getAll
        Mockito.verify(staffDao).getAll()
    }

    @Test
    fun insert() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }
}