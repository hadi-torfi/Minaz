package com.haditorfi.minaz.data.service.provide

import androidx.room.*
import com.haditorfi.minaz.common.formatPriceWithLabel
import com.haditorfi.minaz.data.db.ListConverter
import com.haditorfi.minaz.data.service.Service
import saman.zamani.persiandate.PersianDate
import saman.zamani.persiandate.PersianDateFormat
import java.io.Serializable
import java.util.*

@Entity
data class ProvideService(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    var customerId: Long,
    var personnelId: Long,
    var services: List<Service>,
    var provideDate: Date?,
    var purchase: String,
    var discount: String,
    var totalPrice: String,
    var description: String,
) : Serializable {
    constructor() : this(0, 0, 0, listOf(Service()), null, "", "", "", "")
    constructor(
        customerId: Long,
        personnelId: Long,
        services: List<Service>,
        provideDate: Date?,
        purchase: String,
        discount: String,
        totalPrice: String,
        description: String
    ) : this(
        0,
        customerId,
        personnelId,
        services,
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
    val strPrice get() = "  مبلغ :  ${formatPriceWithLabel(purchase.toLong())}"
    val strTotalPrice get() = "  جمع کل :  ${formatPriceWithLabel(purchase.toLong() - discount.toLong())}"
    val strId get() = " شماره فاکتور : $id"
    val strTime get() = " تاریخ ثبت : ${PersianDateFormat("l Y/m/d").format(PersianDate(provideDate))}"

}
