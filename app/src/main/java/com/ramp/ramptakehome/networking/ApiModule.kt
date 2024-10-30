package com.ramp.ramptakehome.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.lang.reflect.Type
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun gson(
    ): Gson = GsonBuilder()
        .setLenient().create()

    @Singleton
    @Provides
    fun okHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .build()

    @Singleton
    @Provides
    fun api(
        retrofitFactory: RetrofitFactory,
        okHttpClient: OkHttpClient
    ): Api =
        retrofitFactory.getRetrofit(
            "https://ramp-interview.netlify.app/",
            okHttpClient
        ).create(Api::class.java)
}