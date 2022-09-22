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
            .doOnSubscribe { singleLiveEvent.postValue(FishUiState.ShowLoading) }
            .doOnTerminate { singleLiveEvent.postValue(FishUiState.HideLoading) }
            .subscribe({ userList ->
                println("thread nih${Thread.currentThread().name}")
                onSuccess(userList)
            }, { throwable ->
                singleLiveEvent.postValue(FishUiState.Error(throwable))
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