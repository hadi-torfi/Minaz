package com.haditorfi.minaz.data.service.provide

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.haditorfi.minaz.data.customer.Customer
import com.haditorfi.minaz.data.staff.Staff
import com.haditorfi.minaz.data.service.service.Service
import java.io.Serializable

data class Provides(
    @Embedded val provideService: ProvideService,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "id",
    )
    val customer: Customer,

    @Relation(
        parentColumn = "personnelId",
        entityColumn = "id",
    )
    val staff: Staff,

    @Relation(
        parentColumn = "personnelId",
        entityColumn = "id",
    )
    val service: Service,
) : Serializable {
    constructor() : this(ProvideService(), Customer(), Staff(), Service())

    @Ignore
    var activeEditMode: Boolean = false
}
