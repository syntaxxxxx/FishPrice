package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.domain.entity.FishDomainModel
import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.aej.efisheryandroidassessment.data.entity.OptionAreaDtoBean
import com.aej.efisheryandroidassessment.domain.entity.OptionAreaDomainModel
import com.aej.efisheryandroidassessment.presentation.FishUiModel

val dummyListFishDtoBean = listOf(
    FishDtoBean(
        uuid = "721636c6-80ea-48ad-94ac-f663e49b0e39",
        commodity = "FIQRI",
        provinceArea = "LAMPUNG",
        cityArea = "BANDAR LAMPUNG",
        size = "20",
        price = "7400",
        parsedDate = "2022-01-04T23:54:44Z",
        timestamp = "1641340484994"
    )
)

val dummyListFishDomainModel = listOf(
    FishDomainModel(
        uuid = "721636c6-80ea-48ad-94ac-f663e49b0e39",
        commodity = "FIQRI",
        provinceArea = "LAMPUNG",
        cityArea = "BANDAR LAMPUNG",
        size = "20",
        price = "7400",
        parsedDate = "2022-01-04T23:54:44Z",
        timestamp = "1641340484994"
    )
)

val dummyFishUiModelList = listOf(
    FishUiModel(
        uuid = "721636c6-80ea-48ad-94ac-f663e49b0e39",
        commodity = "FIQRI",
        provinceArea = "LAMPUNG",
        cityArea = "BANDAR LAMPUNG",
        size = "20",
        price = "7400",
        parsedDate = "2022-01-04T23:54:44Z",
        timestamp = "1641340484994"
    )
)

val dummyOptionAreaDtoBean = listOf(
    OptionAreaDtoBean(
        province = "ACEH",
        city = "ACEH KOTA"
    )
)

val dummyOptionAreaDomainModelList = listOf(
    OptionAreaDomainModel(
        province = "ACEH",
        city = "ACEH KOTA"
    )
)