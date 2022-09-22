package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.common.SchedulersTrampoline
import com.aej.efisheryandroidassessment.domain.FishRepository
import com.aej.efisheryandroidassessment.domain.FishUseCase
import com.aej.efisheryandroidassessment.domain.OptionAreaUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class OptionAreaUseCaseTest {

    private val repository = mock(FishRepository::class.java)
    private lateinit var useCase: OptionAreaUseCase

    @Before
    fun setup() {
        useCase = OptionAreaUseCase(repository)
        SchedulersTrampoline.setup()
    }

    @Test
    fun `get option area from repository with success`() {
        `when`(repository.optionArea()).thenReturn(
            Single.just(
                dummyOptionAreaDomainModelList
            )
        )

        useCase.execute(Unit).test().apply {
            assertValue { optionAreaUiModelList ->
                println("data nih 1$optionAreaUiModelList")
                println("data nih 2$dummyOptionAreaUiModelList")
                optionAreaUiModelList == dummyOptionAreaUiModelList
            }
            assertComplete()
            assertNoErrors()
        }

        verify(repository, atLeastOnce()).optionArea()
    }

}