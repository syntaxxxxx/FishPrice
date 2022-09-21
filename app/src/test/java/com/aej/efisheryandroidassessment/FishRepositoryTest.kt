package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.common.loadEmptyObjectListJson
import com.aej.efisheryandroidassessment.data.FishRepositoryImpl
import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.domain.FishRepository
import com.aej.efisheryandroidassessment.entity.FishDtoBean
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class FishRepositoryTest {

    private val dataSource = Mockito.mock(RemoteDataSource::class.java)
    private lateinit var repository: FishRepository

    @Before
    fun setup() {
        repository = FishRepositoryImpl(dataSource = dataSource)
    }

    @Test
    fun `search fish price form remote with success`() {
        Mockito.`when`(dataSource.fishPrice()).thenReturn(
            Single.just(
                dummyListFishDtoBean
            )
        )

        repository.fishPrice().test().apply {
            assertValue { listFishDomainModel ->
                println("data nih 1$listFishDomainModel")
                println("data nih 2$dummyListFishDomainModel")
                listFishDomainModel == dummyListFishDomainModel
            }
            assertComplete()
            assertNoErrors()
        }

        Mockito.verify(dataSource, Mockito.atLeastOnce()).fishPrice()
    }

}