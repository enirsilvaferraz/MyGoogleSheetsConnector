package com.eferraz.mygooglesheetsconnector.archtectureImpl.utils

import java.text.NumberFormat

fun Double.toCurrency(): String = NumberFormat.getCurrencyInstance().format(this).toString()