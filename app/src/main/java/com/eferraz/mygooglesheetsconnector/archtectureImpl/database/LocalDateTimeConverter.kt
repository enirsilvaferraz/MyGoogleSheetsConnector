package com.eferraz.mygooglesheetsconnector.archtectureImpl.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.time.LocalDate

@ProvidedTypeConverter
object LocalDateTimeConverter {

    @TypeConverter
    fun toDate(string: String?): LocalDate? =
        if (string == null) null else runCatching { LocalDate.parse(string) }.getOrNull()

    @TypeConverter
    fun toString(date: LocalDate?): String? =
        date?.toString()
}