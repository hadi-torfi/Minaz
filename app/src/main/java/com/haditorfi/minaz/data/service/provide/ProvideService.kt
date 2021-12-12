package com.haditorfi.minaz.data.service.provide

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import java.io.Serializable
import java.util.*

@Entity
data class ProvideService(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var customerId: Long,
    var personnelId: Long,
    var serviceId: Long,
    var provideDate: Date?,
    var purchase: String,
    var discount: String,
    var totalPrice: String,
    var description: String,
) : Serializable {
    constructor() : this(0, 0, 0, 0, null, "", "", "", "")
    constructor(
        customerId: Long,
        personnelId: Long,
        serviceId: Long,
        provideDate: Date?,
        purchase: String,
        discount: String,
        totalPrice: String,
        description: String
    ) : this(
        0,
        customerId,
        personnelId,
        serviceId,
        provideDate,
        purchase,
        discount,
        totalPrice,
        description
    )

    @Ignore
    var activeEditMode: Boolean = false
    val strDiscount get() = "  تخفیف :  ${formatPriceWithLabel(discount.toLong())}"
    val strDescription get() = "  توضیحات :  $description"
    val strTotalPrice get() = "  قیمت :  ${formatPriceWithLabel(purchase.toLong() - discount.toLong())}"
}
