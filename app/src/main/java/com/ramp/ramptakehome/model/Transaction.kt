package com.ramp.ramptakehome.model

import java.math.BigDecimal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class Transaction
    private constructor(
        val id: String,
        val dateTime: LocalDate,
        val clearingTime: LocalDate?,
        val amount: BigDecimal,
        val merchantName: String,
        val merchantCity: String,
        val merchantState: USState?,
        val merchantCategory: String,
        val cardLast4: String,
        val cardDisplayName: String,
        val hasReceipt: String,
        val accountingSyncDate: String?,
        val logoUrl: String
    ) {

    companion object {
        fun fromRaw(rawValue: TransactionRaw) =
            Transaction(
                id = rawValue.id,
                dateTime = rawValue.date.toLocalDateTime()!!,
                clearingTime = rawValue.clearingTime?.toLocalDateTime(),
                amount = rawValue.amount,
                merchantName = rawValue.merchantName,
                merchantCity = rawValue.merchantCity,
                merchantState = USState.fromString(rawValue.merchantState),
                merchantCategory = rawValue.merchantCategory,
                cardLast4 = rawValue.cardLast4,
                cardDisplayName = rawValue.cardDisplayName,
                hasReceipt = rawValue.hasReceipt,
                accountingSyncDate = rawValue.accountingSyncDate,
                logoUrl = rawValue.logoUrl
            )

        private fun String.toLocalDateTime(): LocalDate? {
            if (isNullOrEmpty()) return null
            return LocalDate.parse(trim().replace(" ", "T"), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        }
    }
}