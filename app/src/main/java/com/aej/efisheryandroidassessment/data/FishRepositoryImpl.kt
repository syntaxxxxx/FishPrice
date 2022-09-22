package com.aej.efisheryandroidassessment.data

import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.domain.entity.FishDomainModel
import com.aej.efisheryandroidassessment.domain.FishRepository
import com.aej.efisheryandroidassessment.data.entity.mapToFishDomainModel
import com.aej.efisheryandroidassessment.data.entity.mapToOptionAreaDomainModel
import com.aej.efisheryandroidassessment.domain.entity.OptionAreaDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FishRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource): FishRepository {

    override fun fishPrice(query: String): Single<List<FishDomainModel>> {
        return dataSource.fishPrice(query).map {
            it.mapToFishDomainModel()
        }
    }

    override fun optionArea(): Single<List<OptionAreaDomainModel>> {
        return dataSource.optionArea().map {
            it.mapToOptionAreaDomainModel()
        }
    }

}