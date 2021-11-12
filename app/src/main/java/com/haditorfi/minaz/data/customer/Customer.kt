package com.haditorfi.minaz.data.customer

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Customer(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val tel: String,
    val mobile: String,
) : Parcelable {
    constructor(name: String, tel: String, mobile: String) : this(0, name, tel, mobile)
}
