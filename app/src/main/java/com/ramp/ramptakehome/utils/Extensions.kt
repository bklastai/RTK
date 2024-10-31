package com.ramp.ramptakehome.utils

import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import java.time.LocalDate

fun LocalDate.toUserFriendlyDateSansYear(): String {
    return "${dayOfWeek.name.camelCaseSingleWord()}, " +
            "${month.name.camelCaseSingleWord()} ${dayOfMonth.toUserFriendlyDayOfTheMonth()}"
}

fun String.camelCaseSingleWord(): String {
    return toLowerCase(Locale.current).replaceFirstChar {
        it.uppercaseChar()
    }
}

fun Int.toUserFriendlyDayOfTheMonth(): String {
    return toString() + when(this % 10) {
        1 -> "st"
        2 -> "nd"
        3 -> "rd"
        else -> ""
    }
}

interface ListsContainer<R, T : ListContainer<R>> {
    fun getList(): List<T>
}

interface ListContainer<T> {
    fun getList(): List<T>
}

/* Flattened index points to the first element within this[upperLevelIndex]  */
fun <T : Any, R : ListContainer<T>, E : ListsContainer<T, R>> E.flattenCurrentIndex(upperLevelIndex: Int): Int {
    if (upperLevelIndex >= getList().size) throw IllegalArgumentException("can't flatten index, $upperLevelIndex")
    var result = 0
    for (i in getList().withIndex()) {
        if (i.index >= upperLevelIndex) return result
        result += i.value.getList().size
    }
    throw IllegalStateException("something went terribly wrong, upperLevelIndex = $upperLevelIndex")
}

fun Int.dpsAsPixels(dm: DisplayMetrics): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        toFloat(),
        dm
    ).toInt()
}