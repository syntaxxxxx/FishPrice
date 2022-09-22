package com.aej.efisheryandroidassessment.data.entity

import com.aej.efisheryandroidassessment.domain.entity.OptionAreaDomainModel
import com.google.gson.annotations.SerializedName

data class OptionAreaDtoBean(
    @SerializedName("province") val province: String? = null,
    @SerializedName("city") val city: String? = null,
)

fun OptionAreaDtoBean.mapToOptionAreaDomainModel() =
    OptionAreaDomainModel(
        province = province,
        city = city
    )


fun List<OptionAreaDtoBean>.mapToOptionAreaDomainModel(): List<OptionAreaDomainModel> =
    map { it.mapToOptionAreaDomainModel() }