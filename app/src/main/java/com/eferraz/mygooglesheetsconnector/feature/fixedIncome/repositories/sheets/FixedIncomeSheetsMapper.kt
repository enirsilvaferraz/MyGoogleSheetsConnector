package com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.sheets

import com.eferraz.googlesheets.data.data
import com.eferraz.mygooglesheetsconnector.core.utils.toDate
import com.eferraz.mygooglesheetsconnector.core.utils.toNumber
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncome

object FixedIncomeSheetsMapper {
    fun toModel(data: List<List<Any>>) = data.map { data ->
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
}
