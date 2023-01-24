package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.domain.GetHomeDataUseCase.HomeItem.*
import com.eferraz.mygooglesheetsconnector.core.model.BaseModel
import com.eferraz.mygooglesheetsconnector.core.model.FixedIncome
import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfYear

@Factory
class GetHomeDataUseCase(private val repository: FixedIncomeRoomRepository) {

    operator fun invoke() = combine(

        repository.getFiltered(startDueDate = LocalDate.now().with(firstDayOfMonth()), endDueDate = LocalDate.now()).map {
            FixedIncomeOverDueDate(it)
        },

        repository.getFiltered(liquidity = "Diária").map {
            FixedIncomeLiquidity(it)
        },

        repository.getFiltered(startDueDate = LocalDate.now(), endDueDate = LocalDate.now().plusMonths(3)).map {
            FixedIncome3Months(it)
        },

        repository.getFiltered(startDueDate = LocalDate.now().plusMonths(3), endDueDate = LocalDate.now().with(lastDayOfYear())).map {
            FixedIncomeThisYear(it)
        }

    ) { a, b, c, d ->
        arrayListOf<HomeItem>().apply {
            if (a.data.isNotEmpty()) add(a)
            if (b.data.isNotEmpty()) add(b)
            if (c.data.isNotEmpty()) add(c)
            if (d.data.isNotEmpty()) add(d)
        }
    }

    open class HomeItem(val title: String, val data: List<BaseModel>) {
        data class FixedIncomeOverDueDate(val list: List<FixedIncome>) : HomeItem(title = "Renda fíxa vencida nesse mês", data = list)
        data class FixedIncomeLiquidity(val list: List<FixedIncome>) : HomeItem(title = "Liquidez diária", data = list)
        data class FixedIncome3Months(val list: List<FixedIncome>) : HomeItem(title = "A vencer em menos de 3 meses", data = list)
        data class FixedIncomeThisYear(val list: List<FixedIncome>) : HomeItem(title = "Outros investimentos a vencer em 2023", data = list)
    }
}