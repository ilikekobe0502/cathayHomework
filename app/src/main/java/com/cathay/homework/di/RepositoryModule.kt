package com.cathay.homework.di

import com.cathay.homework.model.api.ApiService
import com.cathay.homework.model.repository.ZooRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    fun provideZooRepository(apiService: ApiService): ZooRepository {
        return ZooRepository(apiService)
    }
}