package com.aej.efisheryandroidassessment.presentation

import com.aej.efisheryandroidassessment.common.presentation.BaseViewModel
import com.aej.efisheryandroidassessment.domain.FishUseCase
import com.aej.efisheryandroidassessment.domain.OptionAreaUseCase
import com.aej.efisheryandroidassessment.presentation.entity.OptionAreaUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FishViewModel @Inject constructor(
    private val fishUseCase: FishUseCase,
    private val optionAreaUseCase: OptionAreaUseCase
) : BaseViewModel<FishUiState>() {

    fun fishPrice(query: String) {
        fishUseCase.execute(FishUseCase.Params(query = query))
            .doOnSubscribe { singleLiveEvent.postValue(FishUiState.ShowLoading) }
            .doOnTerminate { singleLiveEvent.postValue(FishUiState.HideLoading) }
            .subscribe({ userList ->
                println("thread nih${Thread.currentThread().name}")
                onSuccessFishPrice(userList)
            }, { throwable ->
                singleLiveEvent.postValue(FishUiState.Error(throwable))
            }).let(disposable::add)
    }

    private fun onSuccessFishPrice(fishList: List<FishUiModel>) {
        if (fishList.isEmpty()) {
            singleLiveEvent.value = FishUiState.ShowEmpty
        } else {
            fishList.filter { it.uuid != null }.map { fishUiModel ->
                singleLiveEvent.value = FishUiState.Success(fishUiModel)
            }
        }
    }

    fun optionArea() {
        optionAreaUseCase.execute(Unit)
            .doOnSubscribe { singleLiveEvent.postValue(FishUiState.ShowLoading) }
            .doOnTerminate { singleLiveEvent.postValue(FishUiState.HideLoading) }
            .subscribe({ optionAreaList ->
                println("data nih${optionAreaList}")
                onSuccessOptionArea(optionAreaList)
            }, { throwable ->
                singleLiveEvent.postValue(FishUiState.Error(throwable))
            }).let(disposable::add)
    }

    private fun onSuccessOptionArea(optionAreaList: List<OptionAreaUiModel>) {
        if (optionAreaList.isEmpty()) {
            singleLiveEvent.value = FishUiState.ShowEmpty
        } else {
            optionAreaList.map { fishUiModel ->
                singleLiveEvent.value = FishUiState.Success(fishUiModel)
            }
        }
    }

}