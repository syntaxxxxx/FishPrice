package com.aej.efisheryandroidassessment.domain

import com.aej.efisheryandroidassessment.common.domain.SingleUseCase
import com.aej.efisheryandroidassessment.common.singleSchedulersIo
import com.aej.efisheryandroidassessment.domain.entity.mapToFishUiModel
import com.aej.efisheryandroidassessment.presentation.FishUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FishUseCase @Inject constructor(private val repository: FishRepository) : SingleUseCase<FishUseCase.Params, List<FishUiModel>>() {

    override fun buildSingleUseCase(param: Params): Single<List<FishUiModel>> {
        return repository.fishPrice(param.query).compose(singleSchedulersIo()).map {
            it.mapToFishUiModel()
        }
    }

    data class Params(val query: String)

}