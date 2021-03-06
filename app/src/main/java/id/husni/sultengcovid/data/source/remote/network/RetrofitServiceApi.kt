/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.data.source.remote.network

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import id.husni.sultengcovid.utilities.ConsUtilities
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitServiceApi {
    private val gson = GsonConverterFactory.create()
    private val rxJava3Adapter = RxJava3CallAdapterFactory.create()
    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build() as OkHttpClient
    val retrofit = Retrofit.Builder()
        .baseUrl(ConsUtilities.BASE_URL)
        .addConverterFactory(gson)
        .addCallAdapterFactory(rxJava3Adapter)
        .client(okHttpClient)
        .build() as Retrofit
    val retrofitNews = Retrofit.Builder()
        .baseUrl(ConsUtilities.BASE_URL_NEWS)
        .addConverterFactory(gson)
        .client(okHttpClient)
        .build() as Retrofit
}