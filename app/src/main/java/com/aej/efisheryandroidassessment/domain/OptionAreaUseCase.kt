package com.aej.efisheryandroidassessment.domain

import com.aej.efisheryandroidassessment.common.domain.SingleUseCase
import com.aej.efisheryandroidassessment.common.singleSchedulersIo
import com.aej.efisheryandroidassessment.domain.entity.mapToOptionAreaUiModel
import com.aej.efisheryandroidassessment.presentation.entity.OptionAreaUiModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class OptionAreaUseCase @Inject constructor(private val repository: FishRepository) :
    SingleUseCase<Unit, List<OptionAreaUiModel>>() {

    override fun buildSingleUseCase(param: Unit): Single<List<OptionAreaUiModel>> {
        return repository.optionArea().compose(singleSchedulersIo()).map {
            it.mapToOptionAreaUiModel()
        }
    }

}