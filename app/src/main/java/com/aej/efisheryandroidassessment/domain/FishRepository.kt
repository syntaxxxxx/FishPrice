package com.aej.efisheryandroidassessment.domain

import com.aej.efisheryandroidassessment.domain.entity.FishDomainModel
import io.reactivex.rxjava3.core.Single

interface FishRepository {
    fun fishPrice(query: String): Single<List<FishDomainModel>>
}