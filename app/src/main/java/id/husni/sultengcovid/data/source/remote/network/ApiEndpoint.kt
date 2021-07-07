/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.data.source.remote.network

import id.husni.sultengcovid.utilities.ConsUtilities
import id.husni.sultengcovid.data.source.remote.response.DistrictResponse
import id.husni.sultengcovid.data.source.remote.response.HospitalResponse
import id.husni.sultengcovid.data.source.remote.response.NewsResponse
import id.husni.sultengcovid.data.source.remote.response.Province
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {
    //Province
    @GET(ConsUtilities.ENDPOINT_PROVINCE)
    fun getProvinceData() : Observable<Province>
    //District
    @GET(ConsUtilities.ENDPOINT_DISTRICT)
    fun getDistrictData() : Call<DistrictResponse>
    //Hospital
    @GET(ConsUtilities.ENDPOINT_HOSPITAL)
    fun getHospitalData() : Call<HospitalResponse>
    //News
    @GET(ConsUtilities.ENDPOINT_NEWS)
    fun getAllNews(@Query("country") countryCode : String,
                   @Query("category") category : String,
                   @Query("apiKey") apiKey : String)
            : Call<NewsResponse>
}