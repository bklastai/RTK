package com.ramp.ramptakehome.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class TransactionRaw(
    @SerializedName("id")
    val id: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("clearing_time")
    val clearingTime: String?,

    @SerializedName("amount")
    val amount: BigDecimal,

    @SerializedName("merchant_name")
    val merchantName: String,

    @SerializedName("merchant_city")
    val merchantCity: String,

    @SerializedName("merchant_state")
    val merchantState: String,

    @SerializedName("merchant_category")
    val merchantCategory: String,

    @SerializedName("card_last_4")
    val cardLast4: String,

    @SerializedName("card_display_name")
    val cardDisplayName: String,

    @SerializedName("has_receipt")
    val hasReceipt: String,

    @SerializedName("accounting_sync_date")
    val accountingSyncDate: String?,

    @SerializedName("logo_url")
    val logoUrl: String
)