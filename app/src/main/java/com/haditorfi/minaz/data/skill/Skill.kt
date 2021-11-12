package com.haditorfi.minaz.data.skill

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Skill(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
)
