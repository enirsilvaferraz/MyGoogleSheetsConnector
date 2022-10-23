package com.eferraz.mygooglesheetsconnector.usecases

import com.eferraz.finance.domain.archtecture.BaseRepository
import com.eferraz.finance.domain.archtecture.BaseUseCase
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.repositories.FixedIncomePage
import javax.inject.Inject

class GetFixedIncomeListUseCase @Inject constructor(
    @FixedIncomePage private val repo: BaseRepository<@JvmSuppressWildcards List<FixedIncome>>
) : BaseUseCase<Unit, List<FixedIncome>>() {

    override fun invoke(params: Unit) = repo.get()
}