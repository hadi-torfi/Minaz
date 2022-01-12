package com.haditorfi.minaz.data.service.provide

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import com.haditorfi.minaz.data.service.service.Service
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
    var discount: String,
    var description: String,
) : Serializable {
    constructor() : this(0, 0, 0, listOf(Service()), null, "", "")
    constructor(
        customerId: Long,
        personnelId: Long,
        services: List<Service>,
        provideDate: Date?,
        discount: String,
        description: String
    ) : this(
        0,
        customerId,
        personnelId,
        services,
        provideDate,
        discount,
        description
    )

    @Ignore
    var activeEditMode: Boolean = false
    val strDiscount get() = "  تخفیف :  ${formatPriceWithLabel(discount.toLong())}"
    val strDescription get() = if (description.isEmpty()) "توضیحات : ندارد" else "  توضیحات :  $description"
    val strId get() = " شماره فاکتور : $id"
    val itemCount get() = " تعداد خدمات : ${services.size} "
    val strTime get() = " تاریخ ثبت : ${PersianDateFormat("l Y/m/d").format(PersianDate(provideDate))}"
    val strSumPrice get() = "  مبلغ :  ${formatPriceWithLabel(sumPrice())}"
    val strTotalPrice get() = "  جمع کل :  ${formatPriceWithLabel(sumPrice() - discount.toLong())}"

    private fun sumPrice(): Long {
        var sum: Long = 0
        services.forEach {
            sum += it.price.toLong()
        }
        return sum
    }

}
