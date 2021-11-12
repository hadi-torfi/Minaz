package com.haditorfi.minaz.data.customer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val tel: String,
    val mobile: String,
) {
    constructor(name: String, tel: String, mobile: String) : this(0, name, tel, mobile)
}
