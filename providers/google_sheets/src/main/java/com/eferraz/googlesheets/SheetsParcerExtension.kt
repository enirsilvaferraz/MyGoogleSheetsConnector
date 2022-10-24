package com.eferraz.googlesheets

private val alphabets = ('A'..'Z')

fun List<Any>.data(column: Char) = this[alphabets.indexOf(column)] as String