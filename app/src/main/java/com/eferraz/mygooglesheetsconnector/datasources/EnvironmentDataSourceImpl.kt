package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.mygooglesheetsconnector.core.domain.datasources.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import javax.inject.Inject

class EnvironmentDataSourceImpl @Inject constructor() : EnvironmentDataSource {
    override val sheetKey: String = BuildConfig.SHEET_KEY
}