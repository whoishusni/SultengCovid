/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.model.Hospital
import id.husni.sultengcovid.model.HospitalResponse
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalViewModel : ViewModel(){
    private val mutableHospital = MutableLiveData<ArrayList<Hospital>>()
    fun setHospitalData(){
        val retrofit = RetrofitServiceApi.retrofit
        val endPoint = retrofit.create(ApiEndpoint::class.java)
        val call : Call<HospitalResponse> = endPoint.getHospitalData()
        call.enqueue(object : Callback<HospitalResponse>{
            override fun onResponse(call: Call<HospitalResponse>, response: Response<HospitalResponse>) {
                mutableHospital.value = response.body()?.getHospital as ArrayList<Hospital>
            }

            override fun onFailure(call: Call<HospitalResponse>, t: Throwable) {
                //not implemented yet
            }

        })

    }
    fun getHospitalData() : LiveData<ArrayList<Hospital>>{
        return mutableHospital
    }
}