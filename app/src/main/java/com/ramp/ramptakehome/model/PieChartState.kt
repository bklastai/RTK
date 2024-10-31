package com.ramp.ramptakehome.model

import java.math.BigDecimal

data class PieChartState(
    val categories: List<CategoricalSpend>
) {
    val totalSpend = categories.sumOf { it.spend }
}

data class CategoricalSpend(
    val category: String,
    val spend: BigDecimal
)