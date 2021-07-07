/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.husni.sultengcovid.data.source.remote.response.News
import id.husni.sultengcovid.data.source.remote.response.NewsResponse
import id.husni.sultengcovid.data.source.remote.network.ApiEndpoint
import id.husni.sultengcovid.data.source.remote.network.RetrofitServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel(){
    val mutableNews  = MutableLiveData<ArrayList<News>>()
    fun setNewsData(){
        val retrofit = RetrofitServiceApi.retrofitNews
        val endpoint = retrofit.create(ApiEndpoint::class.java)
        val call : Call<NewsResponse> = endpoint.getAllNews("id","health","API")
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