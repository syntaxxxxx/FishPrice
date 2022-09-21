package com.aej.efisheryandroidassessment.common.di

import com.aej.efisheryandroidassessment.data.FishService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ServiceModule {

    @Singleton
    @Provides
    fun provideGithubService(retrofit: Retrofit): FishService {
        return retrofit.create(FishService::class.java)
    }

}