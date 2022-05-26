package com.app.goustotask.di.modules

import android.content.Context
import com.app.goustotask.data.local.repository.AbstractLocalRepository
import com.app.goustotask.data.remote.ApiService
import com.app.goustotask.data.remote.ConnectivityInterceptor
import com.app.goustotask.data.repository.Repository
import com.app.goustotask.data.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        apiService: ApiService,
        localRepository: AbstractLocalRepository,
        context: Context
    ): Repository {
        return RepositoryImpl(apiService, localRepository,context)
    }
}
