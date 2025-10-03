package com.demotest.data.home.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey val name: String,
    val country: String,
    val alphaTwoCode: String,
    val domain: String?,
    val webPage: String?
)