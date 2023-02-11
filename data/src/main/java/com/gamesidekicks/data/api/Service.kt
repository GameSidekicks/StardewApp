package com.gamesidekicks.data.api

import com.gamesidekicks.data.utils.CurlLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Service {
    fun getRetrofit() : IService {

        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(CurlLoggingInterceptor())

        return Retrofit.Builder()
            .baseUrl("https://api.jsonserve.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(builder.build())
            .build()
            .create(IService::class.java)
    }
}
