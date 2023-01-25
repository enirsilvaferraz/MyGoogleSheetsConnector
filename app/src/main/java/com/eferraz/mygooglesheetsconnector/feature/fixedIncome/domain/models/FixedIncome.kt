package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity
data class FixedIncome(
    @PrimaryKey val uuid: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "bank") val bank: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "dueDate") val dueDate: LocalDate?,
    @ColumnInfo(name = "liquidity") val liquidity: String
)