package com.haditorfi.minaz.data.service

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: String,
    val count: String,
    var activeEditMode: Boolean = false,
) : Serializable {
    constructor(name: String, price: String, count: String) : this(0, name, price, count, false)

    val strCount get() = " تعداد : $count عدد "
    val strPrice get() = " قیمت : $price  تومان "
}
