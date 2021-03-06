/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.ui.district

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.data.source.remote.response.District
import id.husni.sultengcovid.data.source.remote.response.DistrictResponse
import id.husni.sultengcovid.data.source.remote.network.ApiEndpoint
import id.husni.sultengcovid.data.source.remote.network.RetrofitServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DistrictViewModel : ViewModel(){
    private val mutableDistrict = MutableLiveData<ArrayList<District>>()
    fun setDistrictData() {
        val retrofit = RetrofitServiceApi.retrofit
        val endpoint = retrofit.create(ApiEndpoint::class.java)
        val call: Call<DistrictResponse> = endpoint.getDistrictData()
        call.enqueue(object : Callback<DistrictResponse> {
            override fun onResponse(call: Call<DistrictResponse>, response: Response<DistrictResponse>) {
                mutableDistrict.value = response.body()?.getDistrictData as ArrayList<District>
            }

            override fun onFailure(call: Call<DistrictResponse>, t: Throwable) {

            }

        })
    }
    fun getDistrictData() : LiveData<ArrayList<District>>{
        return mutableDistrict
    }
}