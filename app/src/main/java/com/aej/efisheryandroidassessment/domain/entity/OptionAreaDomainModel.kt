package com.aej.efisheryandroidassessment.domain.entity

import com.aej.efisheryandroidassessment.presentation.entity.OptionAreaUiModel

data class OptionAreaDomainModel(
    val province: String? = null,
    val city: String? = null,
)

fun OptionAreaDomainModel.mapToOptionAreaUiModel(): OptionAreaUiModel =
    OptionAreaUiModel(
        province = province,
        city = city
    )

fun List<OptionAreaDomainModel>.mapToOptionAreaUiModel(): List<OptionAreaUiModel> =
    map { it.mapToOptionAreaUiModel() }