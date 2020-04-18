package id.husni.sultengcovid.ServiceApi

import id.husni.sultengcovid.Utilities.AppsUtilities
import id.husni.sultengcovid.model.Province
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET(AppsUtilities.ENDPOINT_PROVINCE)
    fun getProvinceData() : Call<Province>
}