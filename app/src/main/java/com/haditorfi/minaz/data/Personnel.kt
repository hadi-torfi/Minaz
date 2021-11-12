package com.haditorfi.minaz.data

import java.util.*

data class Personnel(
    val id: Int,
    val name: String,
    val tel: String,
    val mobile: String,
    val address: String,
    val skills: List<Skill>,
    val birthDay: Date
)
