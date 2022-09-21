package com.aej.efisheryandroidassessment.presentation

import com.aej.efisheryandroidassessment.common.presentation.BaseViewModel
import com.aej.efisheryandroidassessment.domain.FishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FishViewModel @Inject constructor(private val useCase: FishUseCase) :
    BaseViewModel<FishUiState>() {

    fun fishPrice() {
        useCase.execute(Unit)
            .doOnSubscribe { singleLiveEvent.value = FishUiState.ShowLoading }
            .doOnTerminate { singleLiveEvent.value = FishUiState.HideLoading }
            .subscribe({ userList ->
                onSuccess(userList)
            }, { throwable ->
                singleLiveEvent.value = FishUiState.Error(throwable)
            }).let(disposable::add)
    }

    private fun onSuccess(fishList: List<FishUiModel>) {
        if (fishList.isEmpty()) {
            singleLiveEvent.value = FishUiState.ShowEmpty
        } else {
            singleLiveEvent.value = FishUiState.Success(fishList)
        }
    }

}