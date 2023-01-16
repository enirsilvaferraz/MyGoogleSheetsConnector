package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import org.koin.core.annotation.Factory
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Factory
class GetFixedIncomeInReleaseUseCase constructor(private val repository: FixedIncomeRoomRepository) {
    operator fun invoke(params: Params) = repository.getInRelease(params.dueDateParam)

    data class Params(
        val dueDateParam: LocalDate = LocalDate.now().with(TemporalAdjusters.lastDayOfYear())
    )
}

fun definedFiltersThisYear() = GetFixedIncomeInReleaseUseCase.Params(
    dueDateParam = LocalDate.now().with(TemporalAdjusters.lastDayOfYear())
)

fun definedFiltersIn3Months() = GetFixedIncomeInReleaseUseCase.Params(
    dueDateParam = LocalDate.now().plusMonths(3)
)