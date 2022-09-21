package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.common.loadEmptyObjectListJson
import com.aej.efisheryandroidassessment.data.FishService
import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.data.source.RemoteDataSourceImpl
import com.aej.efisheryandroidassessment.entity.FishDtoBean
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RemoteDataSourceTest {

    private val fishService = mock(FishService::class.java)
    private lateinit var dataSource: RemoteDataSource

    val throwable = Throwable("Errors")

    @Before
    fun setup() {
        dataSource = RemoteDataSourceImpl(service = fishService)
    }

    @Test
    fun `search fish price form service with success`() {
        `when`(fishService.fishPrice()).thenReturn(
            Single.just(
                dummyListFishDtoBean
            )
        )

        val type = object : TypeToken<List<FishDtoBean>>(){}.type
        val validJson  = loadEmptyObjectListJson<FishDtoBean>(javaClass.classLoader, "fish_price.json", type)

        dataSource.fishPrice().test().apply {
            assertValue { listFishDtoBean ->
                println("data nih 1$listFishDtoBean")
                println("data nih 2$validJson")
                listFishDtoBean == validJson
            }
            assertComplete()
            assertNoErrors()
        }

        verify(fishService, atLeastOnce()).fishPrice()
    }

    @Test
    fun `search fish price form service with errors`() {
        `when`(fishService.fishPrice()).thenReturn(
            Single.error(throwable)
        )

        dataSource.fishPrice().test().apply {
            assertError(throwable)
        }

        verify(fishService, atLeastOnce()).fishPrice()
    }

}