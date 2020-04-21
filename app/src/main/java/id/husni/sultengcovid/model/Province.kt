/*
 * Copyright (c) 2020.
 * Made with ❤ by Moh Husni Mubaraq
 * Not For Commercial Purpose
 */

package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("data")
    val dataProvince: DataProvince
) {
    class DataProvince(
        @SerializedName("provinsi")
        var provinceName: String,
        @SerializedName("positif")
        var provincePositive: Int,
        @SerializedName("sembuh")
        var provinceRecovered: Int,
        @SerializedName("meninggal")
        var provinceDeath: Int
    )

}