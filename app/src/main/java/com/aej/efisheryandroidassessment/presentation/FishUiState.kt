package com.aej.efisheryandroidassessment.presentation

sealed class FishUiState {
    object ShowLoading: FishUiState()
    object HideLoading: FishUiState()
    object ShowEmpty: FishUiState()

    data class Success<T>(val data: T): FishUiState()
    data class Error(val throwable: Throwable): FishUiState()
}