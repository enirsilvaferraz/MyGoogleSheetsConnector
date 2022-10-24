package com.eferraz.mygooglesheetsconnector

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eferraz.finance.domain.archtecture.DomainResponse
import com.eferraz.finance.domain.entities.FixedIncome
import com.eferraz.mygooglesheetsconnector.usecases.GetFixedIncomeListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

@HiltWorker
class SyncGoogleSheetsWorkManager @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val getFixedIncomeListUseCase: GetFixedIncomeListUseCase,
    //@FixedIncomePage val dataSource: BaseReadableDataSource<@JvmSuppressWildcards List<FixedIncome>>
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {

            val flow: Flow<DomainResponse<List<FixedIncome>>> = getFixedIncomeListUseCase(Unit)

            flow.collect {
                Log.d("ENIR", "Resultado -> ${it.getOrNull()?.size}")
            }

            Log.d("ENIR", "Finalizou")
            Result.success()
        }
    }
}