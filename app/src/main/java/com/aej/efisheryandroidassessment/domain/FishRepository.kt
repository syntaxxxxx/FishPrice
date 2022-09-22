package com.aej.efisheryandroidassessment.domain

import com.aej.efisheryandroidassessment.domain.entity.FishDomainModel
import com.aej.efisheryandroidassessment.domain.entity.OptionAreaDomainModel
import io.reactivex.rxjava3.core.Single

interface FishRepository {
    fun fishPrice(query: String): Single<List<FishDomainModel>>
    fun optionArea(): Single<List<OptionAreaDomainModel>>
}