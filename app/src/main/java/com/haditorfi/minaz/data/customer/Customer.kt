package com.haditorfi.minaz.data.customer

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val tel: String,
    val mobile: String,
) : Serializable {
    constructor(name: String, tel: String, mobile: String) : this(0, name, tel, mobile)
}
