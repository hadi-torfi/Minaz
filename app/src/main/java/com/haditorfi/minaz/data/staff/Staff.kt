package com.haditorfi.minaz.data.staff

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.haditorfi.minaz.R
import com.haditorfi.minaz.common.MANAGER
import com.haditorfi.minaz.common.SECRETARY
import kotlinx.parcelize.Parcelize


@Entity(tableName = "staffs")
@Parcelize
data class Staff(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val mobile: String,
    val address: String,
    val role: String,
) : Parcelable {
    constructor() : this(0, "", "", "", "")
    constructor(
        name: String,
        mobile: String,
        address: String,
        role: String
    ) : this(0, name, mobile, address, role)

    @Ignore
    var activeEditMode: Boolean = false
    val strMobile get() = " موبایل: $mobile"
    val strName get() = " پرسنل : $name"

    fun strRole(): String {
        return when (role) {
            MANAGER -> "مدیر سالن"
            SECRETARY -> "منشی"
            else -> "پرسنل"
        }
    }

    fun setRadioItemIdForRole(): Int {
        return when (role) {
            MANAGER -> R.id.radioManager
            SECRETARY -> R.id.radioSecretary
            else -> R.id.radioPersonnel
        }
    }
}
