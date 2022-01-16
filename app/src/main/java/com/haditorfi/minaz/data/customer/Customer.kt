package com.haditorfi.minaz.data.customer

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val mobile: String,
    val address: String = "",
) : Serializable {
    constructor() : this(0, "", "")
    constructor(name: String, mobile: String, address: String) : this(
        0,
        name,
        mobile,
        address
    )

    @Ignore
    var activeEditMode: Boolean = false
    val strMobile get() = " موبایل: $mobile"
    val strAddress get() = " آدرس: $address"
    val strName get() = " مشتری : $name"

}
