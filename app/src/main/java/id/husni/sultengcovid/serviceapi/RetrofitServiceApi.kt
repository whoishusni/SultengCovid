/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.serviceapi

import id.husni.sultengcovid.utilities.AppsUtilities
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceApi {
    private val gson = GsonConverterFactory.create()
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build() as OkHttpClient
    val retrofit = Retrofit.Builder()
        .baseUrl(AppsUtilities.BASE_URL)
        .addConverterFactory(gson)
        .client(okHttpClient)
        .build() as Retrofit
    val retrofitNews = Retrofit.Builder()
        .baseUrl(AppsUtilities.BASE_URL_NEWS)
        .addConverterFactory(gson)
        .client(okHttpClient)
        .build() as Retrofit
}