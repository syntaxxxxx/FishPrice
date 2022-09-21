package com.aej.efisheryandroidassessment.common.domain

import io.reactivex.rxjava3.core.Single

abstract class SingleUseCase<P, R: Any> {

    abstract fun buildSingleUseCase(param: P): Single<R>

    fun execute(params: P): Single<R> {
        return buildSingleUseCase(params)
    }

}