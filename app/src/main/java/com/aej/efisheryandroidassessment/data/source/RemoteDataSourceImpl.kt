package com.aej.efisheryandroidassessment.data.source

import com.aej.efisheryandroidassessment.data.FishService
import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.aej.efisheryandroidassessment.data.entity.OptionAreaDtoBean
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val service: FishService) : RemoteDataSource {

    override fun fishPrice(query: String): Single<List<FishDtoBean>> {
        return service.fishPrice(query)
    }

    override fun optionArea(): Single<List<OptionAreaDtoBean>> {
        return service.optionArea()
    }

}