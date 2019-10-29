package com.venky.kotlinrecyclerview

import Json4Kotlin_Base
import Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

interface CalInterface{
    @GET("api/v2/holidays?&api_key=67bd3de0eca56f19b197f455ec51505b1f65a699&country=IN&year=2019")
    fun getHolidays():Call<Json4Kotlin_Base>

    companion object Factory{
        fun create(): CalInterface{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://calendarific.com/")
                .build()

            return retrofit.create(CalInterface::class.java)

        }
    }
}