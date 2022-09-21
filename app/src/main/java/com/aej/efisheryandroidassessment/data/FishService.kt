package com.aej.efisheryandroidassessment.data

import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface FishService {

    @GET("/list")
    fun fishPrice(): Single<List<FishDtoBean>>

}