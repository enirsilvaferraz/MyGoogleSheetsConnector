package com.eferraz.mygooglesheetsconnector.core.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.eferraz.googlesheets.data.data
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toDate
import com.eferraz.mygooglesheetsconnector.archtectureImpl.utils.toNumber
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
) : BaseModel

fun List<List<Any>>.toModel() = this.map { data ->
    FixedIncome(
        name = data.data('F'),
        year = data.data('B').toInt(),
        month = data.data('C').toInt(),
        bank = data.data('D'),
        type = data.data('E'),
        group = data.data('Q'),
        target = data.data('R'),
        dueDate = data.data('K').toDate(),
        liquidity = data.data('L'),
        investment = data.data('N').toNumber(),
        amount = data.data('O').toNumber(),
    )
}