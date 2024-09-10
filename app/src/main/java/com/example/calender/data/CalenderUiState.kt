package com.example.calender.data

import java.time.YearMonth

data class CalenderUiState(
    val yearMonth: YearMonth,
    val dates: List<Date>
) {
    companion object {
        val Init = CalenderUiState(
            yearMonth = YearMonth.now(),
            dates = emptyList()
        )
    }

    data class Date(
        val dayOfMonth: String,
        val isSelected: Boolean
    ) {
        companion object {
            val Empty = Date("", false)
        }
    }
}