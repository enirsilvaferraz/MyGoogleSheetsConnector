package com.eferraz.mygooglesheetsconnector

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eferraz.mygooglesheetsconnector.core.domain.usecases.SynchronizeDataBaseUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class SyncGoogleSheetsWorkManager @AssistedInject constructor(
    @Assisted appContext: Context, @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    @Inject
    lateinit var synchronize: SynchronizeDataBaseUseCase

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {

            Log.d("ENIR", "Iniciou")

            // val flow: Flow<DomainResponse<List<FixedIncome>>> = getFixedIncomeListUseCase(Unit)

            //  flow.collect {
            //     Log.d("ENIR", "Resultado -> ${it.getOrNull()?.size}")
            //   }

            Log.d("ENIR", "Finalizou")
            Result.success()
        }
    }
}