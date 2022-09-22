package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.data.FishRepositoryImpl
import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.domain.FishRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FishRepositoryTest {

    private val dataSource = Mockito.mock(RemoteDataSource::class.java)
    private lateinit var repository: FishRepository

    private val throwable = Throwable("Errors")

    private val queryPrice = "7400"

    @Before
    fun setup() {
        repository = FishRepositoryImpl(dataSource = dataSource)
    }

    @Test
    fun `search fish price from remote with success`() {
        Mockito.`when`(dataSource.fishPrice(queryPrice)).thenReturn(
            Single.just(
                dummyListFishDtoBean
            )
        )

        repository.fishPrice(queryPrice).test().apply {
            assertValue { listFishDomainModel ->
                println("data nih 1$listFishDomainModel")
                println("data nih 2$dummyListFishDomainModel")
                listFishDomainModel == dummyListFishDomainModel
            }
            assertComplete()
            assertNoErrors()
        }

        Mockito.verify(dataSource, Mockito.atLeastOnce()).fishPrice(queryPrice)
    }

    @Test
    fun `search fish price from remote with errors`() {
        Mockito.`when`(dataSource.fishPrice(queryPrice)).thenReturn(
            Single.error(throwable)
        )

        repository.fishPrice(queryPrice).test().apply {
            assertError(throwable)
        }

        Mockito.verify(dataSource, Mockito.atLeastOnce()).fishPrice(queryPrice)
    }

    @Test
    fun `get option area from remote with success`() {
        Mockito.`when`(dataSource.optionArea()).thenReturn(
            Single.just(
                dummyOptionAreaDtoBean
            )
        )

        repository.optionArea().test().apply {
            assertValue { optionAreaDomainModelList ->
                println("data nih 1$optionAreaDomainModelList")
                println("data nih 2$dummyOptionAreaDomainModelList")
                optionAreaDomainModelList == dummyOptionAreaDomainModelList
            }
            assertComplete()
            assertNoErrors()
        }

        Mockito.verify(dataSource, Mockito.atLeastOnce()).optionArea()
    }

    @Test
    fun `get option area from remote with errors`() {
        Mockito.`when`(dataSource.optionArea()).thenReturn(
            Single.error(throwable)
        )

        repository.optionArea().test().apply {
            assertError(throwable)
        }

        Mockito.verify(dataSource, Mockito.atLeastOnce()).optionArea()
    }

}