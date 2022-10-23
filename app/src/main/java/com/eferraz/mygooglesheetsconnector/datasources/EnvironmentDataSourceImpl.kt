package com.eferraz.mygooglesheetsconnector.datasources

import com.eferraz.finance.domain.datasources.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import javax.inject.Inject

class EnvironmentDataSourceImpl @Inject constructor() : EnvironmentDataSource {
    override val sheetKey: String = BuildConfig.SHEET_KEY
}