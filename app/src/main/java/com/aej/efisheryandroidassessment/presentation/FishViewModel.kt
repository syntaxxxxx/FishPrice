package com.aej.efisheryandroidassessment.presentation

import com.aej.efisheryandroidassessment.common.presentation.BaseViewModel
import com.aej.efisheryandroidassessment.domain.FishUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.http.Query
import javax.inject.Inject

@HiltViewModel
class FishViewModel @Inject constructor(private val useCase: FishUseCase) :
    BaseViewModel<FishUiState>() {

    fun fishPrice(query: String) {
        useCase.execute(FishUseCase.Params(query = query))
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
            fishList.filter { it.uuid != null }.map { fishUiModel ->
                singleLiveEvent.value = FishUiState.Success(fishUiModel)
            }
        }
    }

}