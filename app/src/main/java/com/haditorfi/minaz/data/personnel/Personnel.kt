package com.haditorfi.minaz.data.personnel

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haditorfi.minaz.data.skill.Skill
import java.util.*

@Entity
data class Personnel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val tel: String? = "",
    val mobile: String,
    val address: String,
    val skills: List<Skill>,
    val birthDay: Date
) {
    constructor(
        name: String,
        tel: String,
        mobile: String,
        address: String,
        skills: List<Skill>,
        birthDay: Date
    ) : this(0, name, tel, mobile, address, skills, birthDay)
}
