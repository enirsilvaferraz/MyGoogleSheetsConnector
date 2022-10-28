package com.eferraz.mygooglesheetsconnector.feature.sync

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eferraz.mygooglesheetsconnector.core.domain.SynchronizeDataBaseUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@HiltWorker
class SyncGoogleSheetsWorkManager @AssistedInject constructor(
    @Assisted appContext: Context, @Assisted workerParams: WorkerParameters, var synchronize: SynchronizeDataBaseUseCase
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d("ENIR", "Sincronização Iniciada!")
            synchronize(Unit)
            Log.d("ENIR", "Sincronização Finalizada com Sucesso!")
            Result.success()
        } catch (e: Exception) {
            Log.d("ENIR", "Sincronização Finalizada com Falha! ${e.message}")
            Result.failure()
        }
    }
}