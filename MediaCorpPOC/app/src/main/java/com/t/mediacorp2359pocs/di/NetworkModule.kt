package com.t.mediacorp2359pocs.di

import com.t.mediacorp2359pocs.presentation.oc171.ApiService
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.protobuf.ProtoConverterFactory
import timber.log.Timber

object NetworkModule {

    private fun providesRetrofit(client: OkHttpClient): Retrofit {
        val endPoint = "https://www.channelnewsasia.com/"

        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    private fun providesOkHttpClient(): OkHttpClient {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (message.shouldPrintLog()) {
                    Timber.d("API: $message")
                }
            }
        }

        val loggingInterceptor = HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun providesRetrofitProto(): Retrofit {
        val endPoint = "https://www.channelnewsasia.com/"
        return Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(ProtoConverterFactory.create())
            .build()
    }

    private fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun String.shouldPrintLog(): Boolean {
        return startsWith("-->") /** Retrofit call **/
            || startsWith("<--") /** Retrofit result **/
            || contains("https://") /** If any url **/
            || contains("http://") /** If any url **/
            || startsWith("{") /** Start of an Object **/
            || startsWith("[") /** Start of a list  **/
    }

    fun createApiService(): ApiService {
        return providesApiService(providesRetrofit(providesOkHttpClient()))
    }

    fun createApiServiceProto(): ApiService {
        return providesApiService(providesRetrofitProto())
    }
}