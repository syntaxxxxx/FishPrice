package com.aej.efisheryandroidassessment.data.source

import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun fishPrice(query: String): Single<List<FishDtoBean>>
}