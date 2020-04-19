package id.husni.sultengcovid.model

import com.google.gson.annotations.SerializedName

data class DistrictResponse(
    @SerializedName("data")
    val getDistrictData : List<District>
)