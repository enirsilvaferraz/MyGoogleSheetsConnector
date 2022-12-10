package com.eferraz.mygooglesheetsconnector.core.datasource

import com.eferraz.googlesheets.data.data
import com.eferraz.mygooglesheetsconnector.archtecture.datasource.ParcelableModel
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import javax.inject.Inject

class FixedIncomeParcelable @Inject constructor() : ParcelableModel<FixedIncome> {

    override fun getRange(): String = "'Histórico Renda Fixa'!A2:Z1000"

    override fun invoke(data: List<Any>): FixedIncome = FixedIncome(
        name = data.data('F'),
        year = data.data('B'),
        month = data.data('C'),
        investment = data.data('N'),
        amount = data.data('O'),
    )
}

