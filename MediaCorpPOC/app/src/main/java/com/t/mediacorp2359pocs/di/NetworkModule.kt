package com.t.mediacorp2359pocs.di

import com.t.mediacorp2359pocs.presentation.oc171.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory

object NetworkModule {

    fun providesRetrofit(): Retrofit {
        val endPoint = "https://www.channelnewsasia.com/"
        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ProtoConverterFactory.create())
            .build()
    }

    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    fun createApiService(): ApiService {
        return providesApiService(providesRetrofit())
    }
}