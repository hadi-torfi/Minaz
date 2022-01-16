package com.haditorfi.minaz.data.product

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "products")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: String,
    val count: String,
) : Parcelable {
    constructor() : this(0, "", "", "")
    constructor(name: String, price: String, count: String) : this(0, name, price, count)

    @Ignore
    var activeEditMode: Boolean = false
    val strPrice get() = "  قیمت :  ${formatPriceWithLabel(price.toLong())}"
    val strCount get() = "  تعداد :  $count عدد "
}
