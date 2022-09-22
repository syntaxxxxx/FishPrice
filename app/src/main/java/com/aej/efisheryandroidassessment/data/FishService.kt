package com.aej.efisheryandroidassessment.data

import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FishService {

    @GET("v1/storages/5e1edf521073e315924ceab4/list")
    fun fishPrice(
        @Query("search") query: String
    ): Single<List<FishDtoBean>>

}