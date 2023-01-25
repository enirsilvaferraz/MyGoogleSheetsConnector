package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.time.LocalDate

@Entity(primaryKeys = ["year", "month", "name"])
data class FixedIncome(
    @ColumnInfo(name = "year") val year: Int, // Historico
    @ColumnInfo(name = "month") val month: Int, // Historico
    @ColumnInfo(name = "name") val name: String,// Model
    @ColumnInfo(name = "bank") val bank: String,// Model
    @ColumnInfo(name = "type") val type: String,// Model
    @ColumnInfo(name = "dueDate") val dueDate: LocalDate?, // Model
    @ColumnInfo(name = "liquidity") val liquidity: String, // Model
    @ColumnInfo(name = "investment") val investment: Double, // Historico
    @ColumnInfo(name = "amount") val amount: Double,// Historico
    @ColumnInfo(name = "group") val group: String, // Calc
    @ColumnInfo(name = "target") val target: String // Historico
)