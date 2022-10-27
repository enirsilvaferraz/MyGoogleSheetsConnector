package com.eferraz.googlesheets.providers

interface SheetsProvider{
    //fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>): DataSourceResponse<List<List<Any>>>
    fun get(sheetID: String, range: String): List<List<Any>>
}