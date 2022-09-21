package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.common.SchedulersTrampoline
import com.aej.efisheryandroidassessment.domain.FishRepository
import com.aej.efisheryandroidassessment.domain.FishUseCase
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class FishUseCaseTest {

    private val repository = mock(FishRepository::class.java)
    private lateinit var useCase: FishUseCase

    @Before
    fun setup() {
        useCase = FishUseCase(repository)
        SchedulersTrampoline.setup()
    }

    @Test
    fun `search fish price from repository with success`() {
        `when`(repository.fishPrice()).thenReturn(
            Single.just(
                dummyListFishDomainModel
            )
        )

        useCase.execute(Unit).test().apply {
            assertValue { fishUiModelList ->
                println("data nih 1$fishUiModelList")
                println("data nih 2$dummyFishUiModelList")
                fishUiModelList == dummyFishUiModelList
            }
            assertComplete()
            assertNoErrors()
        }

        verify(repository, atLeastOnce()).fishPrice()
    }

    @After
    fun tearDown() {
        SchedulersTrampoline.tearDown()
    }

}