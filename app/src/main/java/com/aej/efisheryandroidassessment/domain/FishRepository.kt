package com.aej.efisheryandroidassessment.domain

import io.reactivex.rxjava3.core.Single

interface FishRepository {
    fun fishPrice(): Single<List<FishDomainModel>>
}