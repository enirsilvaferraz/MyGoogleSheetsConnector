package com.eferraz.googlesheets.datasources

interface SheetsDataSource {
    fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>): DataSourceResponse
    fun get(sheetID: String, range: String?): DataSourceResponse
}