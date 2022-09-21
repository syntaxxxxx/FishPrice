package com.aej.efisheryandroidassessment.data.source

import com.aej.efisheryandroidassessment.entity.FishDtoBean
import io.reactivex.rxjava3.core.Single

interface RemoteDataSource {
    fun fishPrice(): Single<List<FishDtoBean>>
}