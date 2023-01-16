package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Factory
class GetFixedIncomeInReleaseUseCase constructor(private val repository: FixedIncomeRoomRepository) {
    operator fun invoke(params: Params) = when (params) {
        is Params.WithLiquidity -> repository.getFiltered(liquidity = params.liquidity)
        is Params.WithStartDueDate -> repository.getFiltered(startDueDate = params.startDueDate, endDueDate = params.endDueDate)
    }

    sealed interface Params {
        class WithStartDueDate(val startDueDate: LocalDate, val endDueDate: LocalDate) : Params
        class WithLiquidity(val liquidity: String) : Params
    }
}

fun definedFiltersThisYear() = GetFixedIncomeInReleaseUseCase.Params.WithStartDueDate(
    startDueDate = definedFiltersIn3Months().endDueDate,
    endDueDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear())
)

fun definedFiltersIn3Months() = GetFixedIncomeInReleaseUseCase.Params.WithStartDueDate(
    startDueDate = LocalDate.now(),
    endDueDate = LocalDate.now().plusMonths(3)
)

fun definedFiltersLiquidity() = GetFixedIncomeInReleaseUseCase.Params.WithLiquidity(
    liquidity = "Diária"
)