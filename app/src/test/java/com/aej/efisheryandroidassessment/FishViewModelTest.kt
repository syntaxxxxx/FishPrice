package com.aej.efisheryandroidassessment

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.aej.efisheryandroidassessment.common.SchedulersTrampoline
import com.aej.efisheryandroidassessment.domain.FishUseCase
import com.aej.efisheryandroidassessment.domain.OptionAreaUseCase
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

    private val fishUseCase = mock(FishUseCase::class.java)
    private val optionAreaUseCase = mock(OptionAreaUseCase::class.java)
    private lateinit var viewModel: FishViewModel
    private val observer = mock<Observer<FishUiState>>()

    private val throwable = Throwable("Errors")

    private val queryPrice = "7400"

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        SchedulersTrampoline.setup()
        viewModel = FishViewModel(fishUseCase, optionAreaUseCase)
        viewModel.singleLiveEvent.observeForever(observer)
        viewModel.singleLiveEvent.observeForever(observer)
    }

    @Test
    fun `search fish price and return list of fish price`() {

        `when`(fishUseCase.execute(FishUseCase.Params(queryPrice))).thenReturn(
            Single.just(dummyFishUiModelList)
        )

        viewModel.fishPrice(queryPrice)

        verify(fishUseCase, atLeastOnce()).execute(FishUseCase.Params(queryPrice))
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        dummyFishUiModelList.map { fishUiModel ->
            verify(observer, atLeastOnce()).onChanged(
                FishUiState.Success(fishUiModel)
            )
        }

        verifyNoMoreInteractions(fishUseCase, observer)
        clearInvocations(fishUseCase, observer)
    }

    @Test
    fun `search fish price and return empty list of fish price`() {

        `when`(fishUseCase.execute(FishUseCase.Params(queryPrice))).thenReturn(
            Single.just(emptyList())
        )

        viewModel.fishPrice(queryPrice)

        verify(fishUseCase, atLeastOnce()).execute(FishUseCase.Params(queryPrice))
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowEmpty)

        verifyNoMoreInteractions(fishUseCase, observer)
        clearInvocations(fishUseCase, observer)
    }

    @Test
    fun `search fish price and return error state`() {

        `when`(fishUseCase.execute(FishUseCase.Params(queryPrice))).thenReturn(
            Single.error(throwable)
        )

        viewModel.fishPrice(queryPrice)

        verify(fishUseCase, atLeastOnce()).execute(FishUseCase.Params(queryPrice))
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.Error(throwable))

        verifyNoMoreInteractions(fishUseCase, observer)
        clearInvocations(fishUseCase, observer)
    }

    @Test
    fun `get option area and return list of option area`() {

        `when`(optionAreaUseCase.execute(Unit)).thenReturn(
            Single.just(dummyOptionAreaUiModelList)
        )

        viewModel.optionArea()

        verify(optionAreaUseCase, atLeastOnce()).execute(Unit)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        dummyOptionAreaUiModelList.map { optionAreaUiModelList ->
            verify(observer, atLeastOnce()).onChanged(FishUiState.Success(optionAreaUiModelList))
        }

        verifyNoMoreInteractions(optionAreaUseCase, observer)
        clearInvocations(optionAreaUseCase, observer)
    }

    @Test
    fun `get option area and return empty list of option area`() {

        `when`(optionAreaUseCase.execute(Unit)).thenReturn(
            Single.just(emptyList())
        )

        viewModel.optionArea()

        verify(optionAreaUseCase, atLeastOnce()).execute(Unit)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.HideLoading)
        verify(observer, atLeastOnce()).onChanged(FishUiState.ShowEmpty)

        verifyNoMoreInteractions(optionAreaUseCase, observer)
        clearInvocations(optionAreaUseCase, observer)
    }

    @After
    fun tearDown() {
        SchedulersTrampoline.tearDown()
    }

}