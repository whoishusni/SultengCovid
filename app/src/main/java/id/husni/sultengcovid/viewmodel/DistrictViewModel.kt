package id.husni.sultengcovid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.model.District
import id.husni.sultengcovid.model.DistrictResponse
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
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
            override fun onResponse(
                call: Call<DistrictResponse>,
                response: Response<DistrictResponse>
            ) {
                mutableDistrict.value = response.body()?.getDistrictData as ArrayList<District>
            }

            override fun onFailure(call: Call<DistrictResponse>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })
    }
    fun getDistrictData() : LiveData<ArrayList<District>>{
        return mutableDistrict
    }
}