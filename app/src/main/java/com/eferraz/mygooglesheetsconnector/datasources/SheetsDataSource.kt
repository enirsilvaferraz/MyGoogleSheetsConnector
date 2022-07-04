package com.eferraz.mygooglesheetsconnector.datasources

interface SheetsDataSource {
    fun append(sheetID: String, range: String?, values: MutableList<MutableList<*>>)
    fun get(sheetID: String, range: String?): MutableList<MutableList<*>>
}