package com.aej.efisheryandroidassessment

import com.aej.efisheryandroidassessment.common.loadEmptyObjectListJson
import com.aej.efisheryandroidassessment.data.FishService
import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.data.source.RemoteDataSourceImpl
import com.aej.efisheryandroidassessment.data.entity.FishDtoBean
import com.aej.efisheryandroidassessment.data.entity.OptionAreaDtoBean
import com.google.gson.reflect.TypeToken
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class RemoteDataSourceTest {

    private val fishService = mock(FishService::class.java)
    private lateinit var dataSource: RemoteDataSource

    private val throwable = Throwable("Errors")

    private val queryPrice = "7400"

    @Before
    fun setup() {
        dataSource = RemoteDataSourceImpl(service = fishService)
    }

    @Test
    fun `search fish price form service with success`() {
        `when`(fishService.fishPrice(queryPrice)).thenReturn(
            Single.just(
                dummyListFishDtoBean
            )
        )

        val type = object : TypeToken<List<FishDtoBean>>(){}.type
        val validJson  = loadEmptyObjectListJson<FishDtoBean>(javaClass.classLoader, "fish_price.json", type)

        dataSource.fishPrice(queryPrice).test().apply {
            assertValue { listFishDtoBean ->
                println("data nih 1$listFishDtoBean")
                println("data nih 2$validJson")
                listFishDtoBean == validJson
            }
            assertComplete()
            assertNoErrors()
        }

        verify(fishService, atLeastOnce()).fishPrice(queryPrice)
    }

    @Test
    fun `search fish price form service with errors`() {
        `when`(fishService.fishPrice(queryPrice)).thenReturn(
            Single.error(throwable)
        )

        dataSource.fishPrice(queryPrice).test().apply {
            assertError(throwable)
        }

        verify(fishService, atLeastOnce()).fishPrice(queryPrice)
    }

    @Test
    fun `get option area from service with success`() {
        `when`(fishService.optionArea()).thenReturn(
            Single.just(
                dummyOptionAreaDtoBean
            )
        )

        val type = object : TypeToken<List<OptionAreaDtoBean>>(){}.type
        val validJson  = loadEmptyObjectListJson<OptionAreaDtoBean>(javaClass.classLoader, "option_area.json", type)

        dataSource.optionArea().test().apply {
            assertValue { optionAreaDtoBeanList ->
                println("data nih 1$optionAreaDtoBeanList")
                println("data nih 2$validJson")
                optionAreaDtoBeanList == validJson
            }
            assertComplete()
            assertNoErrors()
        }

        verify(fishService, atLeastOnce()).optionArea()
    }

    @Test
    fun `get option area from service with errors`() {
        `when`(fishService.optionArea()).thenReturn(
            Single.error(throwable)
        )

        dataSource.optionArea().test().apply {
            assertError(throwable)
        }

        verify(fishService, atLeastOnce()).optionArea()
    }

}