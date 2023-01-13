package com.eferraz.mygooglesheetsconnector.feature.sync

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.eferraz.mygooglesheetsconnector.core.domain.SynchronizeDataBaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SyncGoogleSheetsWorkManager constructor(
    appContext: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams), KoinComponent {

    private val synchronize: SynchronizeDataBaseUseCase by inject()

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            Log.d("ENIR", "Sincronização Iniciada!")
            synchronize().collect()
            Log.d("ENIR", "Sincronização Finalizada com Sucesso!")
            Result.success()
        } catch (e: Exception) {
            Log.d("ENIR", "Sincronização Finalizada com Falha! ${e.message}")
            Result.failure()
        }
    }
}