/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("data")
    val getDistrictData : List<District>
)