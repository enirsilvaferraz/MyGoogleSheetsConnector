package com.eferraz.mygooglesheetsconnector.archtectureImpl.utils

import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Double.toCurrency(): String = NumberFormat.getCurrencyInstance().format(this).toString()

fun String.toNumber() = if (this.isNotBlank()) android.icu.text.NumberFormat.getCurrencyInstance().parse(this).toDouble() else 0.0

fun String.toDate() = runCatching { LocalDate.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy")) }.getOrNull()