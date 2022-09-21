package com.aej.efisheryandroidassessment.data.entity

import com.aej.efisheryandroidassessment.domain.FishDomainModel
import com.google.gson.annotations.SerializedName

data class FishDtoBean(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("komoditas") val commodity: String? = null,
    @SerializedName("area_provinsi") val provinceArea: String? = null,
    @SerializedName("area_kota") val cityArea: String? = null,
    @SerializedName("size") val size: String? = null,
    @SerializedName("price") val price: String? = null,
    @SerializedName("tgl_parsed") val parsedDate: String? = null,
    @SerializedName("timestamp") val timestamp: String? = null,
)

fun FishDtoBean.mapToFishDomainModel(): FishDomainModel =
    FishDomainModel(
        uuid = uuid,
        commodity = commodity,
        provinceArea = provinceArea,
        cityArea = cityArea,
        size = size,
        price = price,
        parsedDate = parsedDate,
        timestamp = timestamp
    )

fun List<FishDtoBean>.mapToFishDomainModel(): List<FishDomainModel> =
    map { it.mapToFishDomainModel() }