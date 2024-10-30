package com.ramp.ramptakehome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramp.ramptakehome.model.Transaction
import com.ramp.ramptakehome.networking.Api
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: Api
) : ViewModel() {

    val transactionsState = MutableStateFlow(emptyList<Transaction>())


    private suspend fun getTransactions(): List<Transaction> {
        return api.getTransactions()
    }

    init {
        viewModelScope.launch {
            transactionsState.emit(getTransactions())
        }
    }
}
