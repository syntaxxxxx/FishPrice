package com.aej.efisheryandroidassessment.common.di

import com.aej.efisheryandroidassessment.data.FishRepositoryImpl
import com.aej.efisheryandroidassessment.data.source.RemoteDataSource
import com.aej.efisheryandroidassessment.data.source.RemoteDataSourceImpl
import com.aej.efisheryandroidassessment.domain.FishRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Singleton
    @Binds
    abstract fun provideRepository(repositoryImpl: FishRepositoryImpl): FishRepository

}