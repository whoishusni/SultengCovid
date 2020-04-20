/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @SerializedName("data")
    val getHospital : List<Hospital>
)