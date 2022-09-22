package com.aej.efisheryandroidassessment.data.source

import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.aej.efisheryandroidassessment.data.entity.OptionAreaDtoBean
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun fishPrice(query: String): Single<List<FishDtoBean>>
    fun optionArea(): Single<List<OptionAreaDtoBean>>
}