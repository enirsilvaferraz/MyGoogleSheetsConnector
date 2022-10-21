package com.eferraz.googlesheets.datasources

interface SheetsDataSource{
    //fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>): DataSourceResponse<List<List<Any>>>
    fun <T> get(sheetID: String, range: String?, transformation: (List<Any>) -> T): SheetsResponse<List<T>>
}