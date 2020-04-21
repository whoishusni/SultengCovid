/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class Hospital(
    @SerializedName("nama")
    var hospitalName : String,
    @SerializedName("alamat")
    var hospitalAddress : String,
    @SerializedName("telepon")
    var hospitalPhone : String,
    @SerializedName("email")
    var hospitalEmail : String
)