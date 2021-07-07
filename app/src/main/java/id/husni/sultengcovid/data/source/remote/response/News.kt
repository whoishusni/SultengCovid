/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class News(
    @SerializedName("source")
    var sourceNews : SourceNews,
    @SerializedName("title")
    var newsTitle : String,
    @SerializedName("url")
    var newsUrl : String,
    @SerializedName("urlToImage")
    var newsUrlToImage : String

)

class SourceNews {
    @SerializedName("name")
    lateinit var sourceName : String
}
