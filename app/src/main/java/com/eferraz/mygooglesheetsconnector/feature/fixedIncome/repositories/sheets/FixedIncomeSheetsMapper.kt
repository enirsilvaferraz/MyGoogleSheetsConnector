package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.sheets

import com.eferraz.googlesheets.data.data
import com.eferraz.mygooglesheetsconnector.core.utils.toDate
import com.eferraz.mygooglesheetsconnector.core.utils.toNumber
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeHistory
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.UUID

data class FixedIncomeSheetsMapper(
    @SerializedName(value = "year") val year: Int,
    @SerializedName(value = "month") val month: Int,
    @SerializedName(value = "name") val name: String,
    @SerializedName(value = "bank") val bank: String,
    @SerializedName(value = "type") val type: String,
    @SerializedName(value = "dueDate") val dueDate: LocalDate?,
    @SerializedName(value = "liquidity") val liquidity: String,
    @SerializedName(value = "investment") val investment: Double,
    @SerializedName(value = "amount") val amount: Double,
    @SerializedName(value = "group") val group: String,
    @SerializedName(value = "target") val target: String
)

fun List<List<Any>>.toModel() = map { list ->

    FixedIncomeSheetsMapper(
        name = list.data('F'),
        year = list.data('B').toInt(),
        month = list.data('C').toInt(),
        bank = list.data('D'),
        type = list.data('E'),
        group = list.data('Q'),
        target = list.data('R'),
        dueDate = list.data('K').toDate(),
        liquidity = list.data('L'),
        investment = list.data('N').toNumber(),
        amount = list.data('O').toNumber(),
    )

}.groupBy { it.name }.mapNotNull { map ->

    with(map.value[0]) {

        val uuid = UUID.randomUUID().toString()

        FixedIncomeWithHistory(
            fixedIncome = FixedIncome(
                uuid = uuid,
                name = name,
                bank = bank,
                type = type,
                dueDate = dueDate,
                liquidity = liquidity
            ),
            history = map.value.map {
                FixedIncomeHistory(
                    year = it.year,
                    month = it.month,
                    investment = it.investment,
                    amount = it.amount,
                    target = it.target,
                    fixedIncomeUuid = uuid
                )
            }
        )
    }
}
