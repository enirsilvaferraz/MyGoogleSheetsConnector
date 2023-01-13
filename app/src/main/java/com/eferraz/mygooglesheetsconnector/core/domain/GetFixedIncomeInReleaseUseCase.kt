package com.eferraz.mygooglesheetsconnector.core.domain

import com.eferraz.mygooglesheetsconnector.core.repositories.FixedIncomeRoomRepository
import org.koin.core.annotation.Factory

@Factory
class GetFixedIncomeInReleaseUseCase constructor(private val repository: FixedIncomeRoomRepository) {
    operator fun invoke() = repository.getInRelease()
}