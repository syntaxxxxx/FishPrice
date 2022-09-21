package com.aej.efisheryandroidassessment.data

import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.domain.FishDomainModel
import com.aej.efisheryandroidassessment.domain.FishRepository
import com.aej.efisheryandroidassessment.entity.mapToFishDomainModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FishRepositoryImpl @Inject constructor(private val dataSource: RemoteDataSource): FishRepository {

    override fun fishPrice(): Single<List<FishDomainModel>> {
        return dataSource.fishPrice().map {
            it.mapToFishDomainModel()
        }
    }

}