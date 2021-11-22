package com.haditorfi.minaz.data.personnel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haditorfi.minaz.data.skill.Skill
import java.io.Serializable

@Entity
data class Personnel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val mobile: String,
    val tel: String? = "",
    val address: String,
    val skill: String,
    val role: String,
    var activeEditMode: Boolean = false
):Serializable {
    constructor(
        name: String,
        tel: String,
        mobile: String,
        address: String,
        skill: String,
        role: String
    ) : this(0, name, tel, mobile, address, skill, role, false)

    val strMobile get() = " موبایل: $mobile"
    val strAddress get() = " آدرس: $address"
}
