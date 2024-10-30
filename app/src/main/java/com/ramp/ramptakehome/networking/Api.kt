package com.ramp.ramptakehome.networking

import com.ramp.ramptakehome.model.Transaction
import retrofit2.http.GET

interface Api {
    @GET("transactions.json")
    suspend fun getTransactions(): List<Transaction>
}
