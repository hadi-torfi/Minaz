package com.haditorfi.minaz.feature

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.haditorfi.minaz.common.PERSONNEL
import com.haditorfi.minaz.data.db.AppDatabase
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.data.staff.StaffDao
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class StaffViewModelTest : TestCase() {
    private lateinit var staffDao: StaffDao
    private lateinit var db: AppDatabase

    @Before
    override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        staffDao = db.staffDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun readAndWriteStaff() = runBlocking {
        val staff = Staff("مهناز عبدی", "09352626000", "تهران", PERSONNEL)
        staffDao.insert(staff)
        val staffs = staffDao.getAll()
        //  assertThat(staffs.value?.contains(staff)).isTrue()
    }
}