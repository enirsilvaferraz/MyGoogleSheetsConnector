package com.eferraz.mygooglesheetsconnector.core.network

import com.eferraz.mygooglesheetsconnector.core.network.EnvironmentDataSource
import com.eferraz.mygooglesheetsconnector.BuildConfig
import javax.inject.Inject

class EnvironmentDataSourceImpl @Inject constructor() : EnvironmentDataSource {
    override val sheetKey: String = BuildConfig.SHEET_KEY
}