package com.ramp.ramptakehome.model

import com.ramp.ramptakehome.utils.ListContainer
import com.ramp.ramptakehome.utils.ListsContainer
import java.time.LocalDate

data class DaySpend(
    // sorted by time of day
    val transactions: List<Transaction>,
    val date: LocalDate
): ListContainer<Transaction> {
    override fun getList(): List<Transaction> {
        return transactions
    }
    val totalSpend get() = transactions.sumOf { it.amount }

    fun localSpendRatio(
        userResidenceState: USState = USState.WA
    ): Float = transactions.count { transaction ->
        transaction.merchantState == userResidenceState
    }.toFloat() / transactions.size.toFloat()

    val pieChart: PieChartState get() = PieChartState(
        transactions.groupBy { it.merchantCategory }.map {
            CategoricalSpend(it.key, it.value.sumOf { a -> a.amount })
        }
    )
}

data class MonthSpend(
    val daySpends: List<DaySpend>
): ListsContainer<Transaction, DaySpend> {
    override fun getList() = daySpends
}

data class AllSpend(
    val allSpends: List<DaySpend> = listOf()
): ListsContainer<Transaction, DaySpend> {
    override fun getList() = allSpends
}