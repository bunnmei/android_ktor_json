package com.example.calender.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calender.data.CalendarDateSource
import com.example.calender.data.CalenderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.YearMonth

@RequiresApi(Build.VERSION_CODES.O)
class CalendarViewModel: ViewModel() {

    private val dataSource by lazy { CalendarDateSource() }
    private val _uiState = MutableStateFlow(CalenderUiState.Init)
    val uiState: StateFlow<CalenderUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    dates = dataSource.getDates(currentState.yearMonth)
                )
            }
        }
    }

    fun toNextMonth(nextMonth: YearMonth) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    yearMonth = nextMonth,
                    dates = dataSource.getDates(nextMonth)
                )
            }
        }
    }

    fun toPreviousMonth(prevMonth: YearMonth) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    yearMonth = prevMonth,
                    dates = dataSource.getDates(prevMonth)
                )
            }
        }
    }
}