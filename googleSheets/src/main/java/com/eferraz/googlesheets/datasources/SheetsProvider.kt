package com.eferraz.googlesheets.datasources

interface SheetsProvider{
    //fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>): DataSourceResponse<List<List<Any>>>
    fun get(sheetID: String, range: String): SheetsResponse<List<List<Any>>>
}