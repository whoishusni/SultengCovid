/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class Hospital(
    @SerializedName("nama")
    val hospitalName : String,
    @SerializedName("alamat")
    val hospitalAddress : String,
    @SerializedName("telepon")
    val hospitalPhone : String,
    @SerializedName("email")
    val hospitalEmail : String
)