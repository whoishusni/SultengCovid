package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class Province(
    @SerializedName("data")
    val dataProvince: DataProvince
) {
    class DataProvince(
        @SerializedName("provinsi")
        val provinceName: String,
        @SerializedName("positif")
        val provincePositive: String,
        @SerializedName("sembuh")
        val provinceRecovered: String,
        @SerializedName("meninggal")
        val provinceDeath: String
    )

}