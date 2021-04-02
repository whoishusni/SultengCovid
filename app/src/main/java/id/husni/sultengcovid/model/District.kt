/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class District(
    @SerializedName("kabupaten")
    var districtName : String,
    @SerializedName("ODP")
    var districtOdp : Int,
    @SerializedName("PDP")
    var districtPdp : Int,
    @SerializedName("positif")
    var districtPositive : Int,
    @SerializedName("negatif")
    var districtNegative : Int,
    @SerializedName("meninggal")
    var districtDeaths : Int
)