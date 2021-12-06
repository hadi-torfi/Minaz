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
    val discount: String,
    var description: String,
) : Serializable {
    constructor() : this(0, 0, 0, 0, null, "",  "")
    constructor(
        customerId: Long,
        personnelId: Long,
        serviceId: Long,
        provideDate: Date?,
        discount: String,
        description: String
    ) : this(
        0,
        customerId,
        personnelId,
        serviceId,
        provideDate,
        discount,
        description
    )

    @Ignore
    var activeEditMode: Boolean = false
    val strDiscount get() = "  تخفیف :  ${formatPriceWithLabel(discount.toLong())}"
}
