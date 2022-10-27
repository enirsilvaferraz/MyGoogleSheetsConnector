package com.eferraz.mygooglesheetsconnector.core.datasource.remote

import com.eferraz.googlesheets.data.data
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import javax.inject.Inject

class FixedIncomeParsable @Inject constructor() : ParsableModel<FixedIncome> {
    override fun invoke(data: List<Any>): FixedIncome = FixedIncome(
        name = data.data('F'),
        year = data.data('B'),
        month = data.data('C'),
        investment = data.data('N'),
        amount = data.data('O'),
    )
}

