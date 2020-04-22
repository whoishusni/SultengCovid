/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.model.News
import id.husni.sultengcovid.model.NewsResponse
import id.husni.sultengcovid.serviceapi.ApiEndpoint
import id.husni.sultengcovid.serviceapi.RetrofitServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel(){
    val mutableNews  = MutableLiveData<ArrayList<News>>()
    fun setNewsData(){
        val retrofit = RetrofitServiceApi.retrofitNews
        val endpoint = retrofit.create(ApiEndpoint::class.java)
        val call : Call<NewsResponse> = endpoint.getAllNews("id","health","2cc332eb731143b09ed881581ca57057")
        call.enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                mutableNews.value = response.body()?.getArticles as ArrayList<News>
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {

            }

        })
    }

    fun getNewsData() : LiveData<ArrayList<News>>{
        return mutableNews
    }
}