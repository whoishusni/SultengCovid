/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.serviceapi

import id.husni.sultengcovid.utilities.AppsUtilities
import id.husni.sultengcovid.model.DistrictResponse
import id.husni.sultengcovid.model.HospitalResponse
import id.husni.sultengcovid.model.Province
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    //Province
    @GET(AppsUtilities.ENDPOINT_PROVINCE)
    fun getProvinceData() : Call<Province>
    //District
    @GET(AppsUtilities.ENDPOINT_DISTRICT)
    fun getDistrictData() : Call<DistrictResponse>
    //Hospital
    @GET(AppsUtilities.ENDPOINT_HOSPITAL)
    fun getHospitalData() : Call<HospitalResponse>
}