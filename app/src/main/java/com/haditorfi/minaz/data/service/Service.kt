package com.haditorfi.minaz.data.service

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: Int,
    val count: Int,
):Serializable {
    constructor(name: String, price: Int, count: Int) : this(0, name, price, count)
}
