package com.aej.efisheryandroidassessment.data.source

import com.aej.efisheryandroidassessment.data.FishService
import com.aej.efisheryandroidassessment.entity.FishDtoBean
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: FishService) : RemoteDataSource {

    override fun fishPrice(): Single<List<FishDtoBean>> {
        return service.fishPrice()
    }

}