package com.haditorfi.minaz.data.product

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import java.io.Serializable

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: String,
    val count: String,
) : Serializable {
    constructor(name: String, price: String, count: String) : this(0, name, price, count)

    var activeEditMode: Boolean = false
    val strPrice get() = "  قیمت :  ${formatPriceWithLabel(price.toLong())}"
    val strCount get() = "  تعداد :  $count عدد "
}
