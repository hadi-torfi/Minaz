package com.haditorfi.minaz.data.service.service

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.haditorfi.minaz.common.formatPriceWithLabel
import kotlinx.parcelize.Parcelize

@Entity(tableName = "services")
@Parcelize
data class Service(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val price: String,
) : Parcelable {
    constructor() : this(0, "", "")
    constructor(name: String, price: String) : this(0, name, price)

    @Ignore
    var activeEditMode: Boolean = false
    val strPrice get() = formatPriceWithLabel(price.toLong())
}
