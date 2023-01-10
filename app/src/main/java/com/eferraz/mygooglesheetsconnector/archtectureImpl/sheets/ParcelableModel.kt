package com.eferraz.mygooglesheetsconnector.archtectureImpl.sheets

interface ParcelableModel<Result> {
    operator fun invoke(data: List<Any>): Result
}