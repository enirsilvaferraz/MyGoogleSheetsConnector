package com.eferraz.mygooglesheetsconnector.feature.home.domain

import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.domain.models.FixedIncomeWithHistory
import com.eferraz.mygooglesheetsconnector.feature.fixedIncome.repositories.room.FixedIncomeWithHistoryRoomRepository
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase.HomeItem.FixedIncome3Months
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase.HomeItem.FixedIncomeLiquidity
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase.HomeItem.FixedIncomeOverDueDate
import com.eferraz.mygooglesheetsconnector.feature.home.domain.GetHomeDataUseCase.HomeItem.FixedIncomeThisYear
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters.firstDayOfMonth
import java.time.temporal.TemporalAdjusters.lastDayOfYear

@Factory
class GetHomeDataUseCase(private val repository: FixedIncomeWithHistoryRoomRepository) {

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

    open class HomeItem(val title: String, val data: List<Any>) {
        data class FixedIncomeOverDueDate(val list: List<FixedIncomeWithHistory>) : HomeItem(title = "Renda fíxa vencida nesse mês", data = list)
        data class FixedIncomeLiquidity(val list: List<FixedIncomeWithHistory>) : HomeItem(title = "Liquidez diária", data = list)
        data class FixedIncome3Months(val list: List<FixedIncomeWithHistory>) : HomeItem(title = "A vencer em menos de 3 meses", data = list)
        data class FixedIncomeThisYear(val list: List<FixedIncomeWithHistory>) : HomeItem(title = "Outros investimentos a vencer em 2023", data = list)
    }
}
