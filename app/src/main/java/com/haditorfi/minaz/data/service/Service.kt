package com.haditorfi.minaz.data.service

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import java.io.Serializable

@Entity
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: String,
) : Serializable {
    constructor(name: String, price: String) : this(0, name, price)

    var activeEditMode: Boolean = false
    val strPrice get() = "  قیمت :  ${formatPriceWithLabel(price.toInt())}"
}
