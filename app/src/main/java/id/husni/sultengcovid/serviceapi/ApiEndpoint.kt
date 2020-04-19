package id.husni.sultengcovid.serviceapi

import id.husni.sultengcovid.utilities.AppsUtilities
import id.husni.sultengcovid.model.DistrictResponse
import id.husni.sultengcovid.model.Province
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {
    @GET(AppsUtilities.ENDPOINT_PROVINCE)
    fun getProvinceData() : Call<Province>
    @GET(AppsUtilities.ENDPOINT_DISTRICT)
    fun getDistrictData() : Call<DistrictResponse>
}