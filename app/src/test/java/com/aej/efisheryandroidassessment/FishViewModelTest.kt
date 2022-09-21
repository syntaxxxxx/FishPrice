package com.aej.efisheryandroidassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aej.efisheryandroidassessment.common.SchedulersTrampoline
import com.aej.efisheryandroidassessment.domain.FishUseCase
import com.aej.efisheryandroidassessment.presentation.FishUiState
import com.aej.efisheryandroidassessment.presentation.FishViewModel
import io.reactivex.rxjava3.core.Single
import com.nhaarman.mockito_kotlin.mock
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class FishViewModelTest {

    private val useCase = mock(FishUseCase::class.java)
    private lateinit var viewModel: FishViewModel
    private val observer = mock<Observer<FishUiState>>()

    private val throwable = Throwable("Errors")

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        SchedulersTrampoline.setup()
        viewModel = FishViewModel(useCase)
        viewModel.singleLiveEvent.observeForever(observer)
        viewModel.singleLiveEvent.observeForever(observer)
    }

    @Test
    fun`search fish price and return list of fish price`() {

        `when`(useCase.execute(Unit)).thenReturn(
            Single.just(dummyFishUiModelList)
        )

        viewModel.fishPrice()

        verify(useCase, atLeastOnce()).execute(Unit)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.Success(dummyFishUiModelList))

        verifyNoMoreInteractions(useCase, observer)
        clearInvocations(useCase, observer)
    }

    @Test
    fun`search fish price and return empty list of fish price`() {

        `when`(useCase.execute(Unit)).thenReturn(
            Single.just(emptyList())
        )

        viewModel.fishPrice()

        verify(useCase, atLeastOnce()).execute(Unit)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowEmpty)

        verifyNoMoreInteractions(useCase, observer)
        clearInvocations(useCase, observer)
    }

    @Test
    fun`search fish price and return error state`() {

        `when`(useCase.execute(Unit)).thenReturn(
            Single.error(throwable)
        )

        viewModel.fishPrice()

        verify(useCase, atLeastOnce()).execute(Unit)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.Error(throwable))

        verifyNoMoreInteractions(useCase, observer)
        clearInvocations(useCase, observer)
    }

    @After
    fun tearDown() {
        SchedulersTrampoline.tearDown()
    }

}