package com.haditorfi.minaz.data.personnel

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Personnel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val mobile: String,
    val address: String,
    val role: String,
) : Serializable {
    constructor(
        name: String,
        mobile: String,
        address: String,
        role: String
    ) : this(0, name, mobile, address, role)

    var activeEditMode: Boolean = false
    val strMobile get() = " موبایل: $mobile"
    val strAddress get() = " آدرس: $address"
}
