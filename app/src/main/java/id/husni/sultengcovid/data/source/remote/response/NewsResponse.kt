/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles")
    var getArticles : List<News>
)