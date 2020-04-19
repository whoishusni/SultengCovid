package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

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
)