/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class District(
    @SerializedName("kabupaten")
    val districtName : String,
    @SerializedName("ODP")
    val districtOdp : Int,
    @SerializedName("PDP")
    val districtPdp : Int,
    @SerializedName("positif")
    val districtPositive : Int,
    @SerializedName("negatif")
    val districtNegative : Int,
    @SerializedName("meninggal")
    val districtDeaths : Int
) : Parcelable