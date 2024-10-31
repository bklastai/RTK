package com.ramp.ramptakehome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramp.ramptakehome.model.AllSpend
import com.ramp.ramptakehome.model.Transaction
import com.ramp.ramptakehome.model.DaySpend
import com.ramp.ramptakehome.model.MonthSpend
import com.ramp.ramptakehome.networking.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    val daysSpends = MutableStateFlow(AllSpend())

    private suspend fun getTransactions(): List<Transaction> {
        return api.getTransactions().map {
            Transaction.fromRaw(it)
        }
    }

    init {
        viewModelScope.launch {
            val updatedDaySpends = getTransactions().groupBy {
                LocalDate.parse(it.dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE))
            }.toSortedMap().map {
                DaySpend(it.value, it.key)
            }
            daysSpends.emit(AllSpend(updatedDaySpends))
        }
    }

}
