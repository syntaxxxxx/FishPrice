package com.aej.efisheryandroidassessment.data.entity

import com.google.gson.annotations.SerializedName

data class OptionAreaDtoBean(
    @SerializedName("province") val province: String? = null,
    @SerializedName("city") val city: String? = null,
)