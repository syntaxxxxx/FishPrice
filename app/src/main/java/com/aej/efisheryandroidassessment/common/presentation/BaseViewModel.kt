package com.aej.efisheryandroidassessment.common.presentation

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel<T>: ViewModel() {

    val singleLiveEvent = SingleLiveEvent<T>()

    val disposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}