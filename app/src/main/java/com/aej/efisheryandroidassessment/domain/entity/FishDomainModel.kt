package com.aej.efisheryandroidassessment.domain.entity

import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.aej.efisheryandroidassessment.presentation.FishUiModel

data class FishDomainModel(
    val uuid: String? = null,
    val commodity: String? = null,
    val provinceArea: String? = null,
    val cityArea: String? = null,
    val size: String? = null,
    val price: String? = null,
    val parsedDate: String? = null,
    val timestamp: String? = null,
)

fun FishDomainModel.mapToFishUiModel(): FishUiModel =
    FishUiModel(
        uuid = uuid,
        commodity = commodity,
        provinceArea = provinceArea,
        cityArea = cityArea,
        size = size,
        price = price,
        parsedDate = parsedDate,
        timestamp = timestamp
    )

fun List<FishDomainModel>.mapToFishUiModel(): List<FishUiModel> =
    map { it.mapToFishUiModel() }