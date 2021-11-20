package com.haditorfi.minaz.data.customer

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val mobile: String,
    val address: String = "",
    var activeEditMode:Boolean = false
) : Serializable {
    constructor(name: String, mobile: String, address: String) : this(
        0,
        name,
        mobile,
        address,
        false
    )

    val strMobile get() = " موبایل: $mobile"
    val strAddress get() = " آدرس: $address"

}
