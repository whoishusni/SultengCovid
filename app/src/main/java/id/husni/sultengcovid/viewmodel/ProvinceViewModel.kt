package id.husni.sultengcovid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import id.husni.sultengcovid.model.Province
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProvinceViewModel : ViewModel() {
    val provinceMute = MutableLiveData<Province>()
    fun setProvinceData() {
        val retrofit = RetrofitServiceApi.retrofit
        val apiEndpoint = retrofit.create(ApiEndpoint::class.java)
        val call: Call<Province> = apiEndpoint.getProvinceData()
        call.enqueue(object : Callback<Province> {
            override fun onResponse(call: Call<Province>, response: Response<Province>) {
                provinceMute.value = response.body()
            }

            override fun onFailure(call: Call<Province>, t: Throwable) {
                TODO("not implemented")
            }
        })
    }

    fun getProvinceLiveData(): MutableLiveData<Province> {
        return provinceMute
    }
}